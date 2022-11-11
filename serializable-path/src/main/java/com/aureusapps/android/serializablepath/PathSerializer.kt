package com.aureusapps.android.serializablepath

import android.graphics.Path
import android.graphics.PathMeasure
import android.graphics.PointF
import com.aureusapps.android.serializablepath.serializers.PointFAsMap
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ArraySerializer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Suppress("NAME_SHADOWING")
@OptIn(ExperimentalSerializationApi::class)

class PathSerializer {

    companion object {

        fun serialize(path: Path, error: Float = 0.5f): String {
            val measure = PathMeasure(path, false)
            val contours = mutableListOf<Contour>()
            val pos = floatArrayOf(0f, 0f)
            do {
                var len = 0f
                val points = mutableListOf<PointF>()
                while (len <= measure.length) {
                    measure.getPosTan(len, pos, null)
                    points.add(PointF(pos[0], pos[1]))
                    len += error
                }
                contours.add(Contour(points))
            } while (measure.nextContour())
            return Json.encodeToString(contours)
        }

        fun deserialize(serializedPath: String): Path {
            val serializer = ArraySerializer(Contour.serializer())
            val contours = Json.decodeFromString(serializer, serializedPath)
            val path = Path()
            contours.forEach { contour ->
                val points = contour.points
                if (points.isNotEmpty()) {
                    var p = points[0]
                    path.moveTo(p.x, p.y)
                    for (i in 1 until points.size) {
                        p = points[i]
                        path.lineTo(p.x, p.y)
                    }
                }
            }
            return path
        }

    }

    @Serializable
    internal class Contour(val points: List<PointFAsMap>)
}