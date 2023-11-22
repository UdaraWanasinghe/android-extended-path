package com.aureusapps.android.extendedpath

import androidx.core.graphics.PathParser
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExtendedPathInstrumentedTest {

    companion object {
        private const val MAIN_PATH_DATA =
            "m79,226c0,-1 1.29289,-0.29289 2,-1c0.70711,-0.70711 1.1731,-1.85274 2,-3c1.30745,-1.814 2.14429,-3.93414 3,-6c1.21015,-2.92157 1.49346,-5.87856 2,-9c0.64073,-3.94835 -1.70405,-7.71976 -3,-12c-2.33631,-7.71632 -2.69125,-13.07179 -4,-18c-0.81165,-3.05634 -3,-7 -4,-10c-1,-3 -1.11587,-5.96384 -2,-9c-1.15277,-3.95868 -1,-7 -1,-11c0,-5 -0.36655,-9.02248 0,-12c0.50378,-4.09221 1.08099,-8.107 2,-12c1.02749,-4.3525 4.67947,-7.57111 7,-12c1.46764,-2.80108 2.70546,-6.34619 5,-8c3.62798,-2.61491 7,-2 10,-2c3,0 8.11237,-0.45295 13,1c5.16191,1.53448 10.37202,3.38509 14,6c3.4418,2.48071 4.63872,4.44298 7,6c4.49576,2.96449 8.45049,4.45049 11,7c2.54951,2.54951 5.22273,5.72399 9,9c3.20512,2.77979 6,6 9,9c3,3 5.56342,6.34235 8,9c4.32713,4.71974 8.80565,7.44841 13,9c3.867,1.4305 8.90164,3.91779 14,4c6.08197,0.09807 15,-2 24,-5c9,-3 18.09315,-5.79665 27,-10c8.08878,-3.81729 12.67136,-7.49741 17,-10c6.59317,-3.81183 10.83127,-7.22054 16,-11c5.82092,-4.25637 14.18271,-7.60522 21,-11c7.21698,-3.5938 15.02173,-5.91502 23,-8c11.03128,-2.88283 15.92578,-4.49755 21,-5c4.97568,-0.49269 10,0 14,0c3,0 5.71411,3.21168 8,6c2.68979,3.28101 4.92554,7.62061 7,14c1.88107,5.7846 1,12 1,17c0,5 0.90338,11.01691 2,17c0.91925,5.01547 2.49295,11.23401 5,16c3.35712,6.38199 6.724,10.22273 10,14c1.85318,2.13675 4,2 7,2c5,0 11.08441,-0.52528 18,-2c7.12003,-1.51831 16.37848,-5.15994 26,-10c13.6362,-6.85962 34.4848,-23.27786 53,-30c20.52917,-7.45334 42.89453,-11.9147 65,-14c18.9422,-1.7869 34.39337,-2.53554 45,1c10.60663,3.53554 15.72961,11.22885 20,16c4.71582,5.26884 10.7796,13.11406 15,21c3.80426,7.10829 8.51062,15.26401 13,23c3.61945,6.23695 6.47546,11.86194 8,18c1.46625,5.9034 0.6015,11.00993 1,19c0.50061,10.0374 0.29846,25.08649 2,36c1.42023,9.1095 3.35724,15.25406 6,21c2.43652,5.29749 4.83868,9.1705 8,12c4.77112,4.27039 7.55817,7.51929 11,10c1.81396,1.30746 5.55817,4.51929 9,7c3.62799,2.6149 7.21259,3.6712 12,7c5.25714,3.65543 8,8 11,12c3,4 6.43286,9.80243 9,16c2.42029,5.84314 5.19086,14.95468 7,23c1.11865,4.97479 1.91901,10.10699 1,14c-0.51373,2.17624 -1.55817,5.51929 -5,8c-5.44196,3.92236 -10.88,5.48169 -18,7c-6.91559,1.4747 -16.2345,-0.74069 -30,2c-6.20282,1.23499 -11,4 -15,7c-4,3 -8,6 -10,10c-2,4 -3,9 -3,15c0,6 0,18 1,27c1,9 2.39294,16.40771 0,22c-1.96698,4.59683 -6.88855,8.19409 -12,11c-5.88043,3.22806 -11.92023,7.25085 -21,10c-10.91254,3.30405 -23.12701,4.80841 -32,3c-10.22998,-2.08499 -21.05414,-4.43268 -24,-5c-5.28802,-1.01837 -16.45538,-9.60818 -25,-20c-9.52673,-11.58624 -19.40894,-24.56235 -28,-33c-6.57767,-6.46021 -14.87592,-12.586 -23,-14c-9.90103,-1.72327 -19.0072,-1.3172 -26,-1c-11.03403,0.50052 -22.52048,5.13123 -33,12c-9.53592,6.25031 -14.65106,13.43283 -22,19c-5.74799,4.35437 -15.51273,13.75366 -26,20c-9.60562,5.72122 -16.74402,7.7702 -24,13c-5.73636,4.13452 -10.67841,8.75558 -17,11c-8.6882,3.08466 -17.95078,3.88495 -28,4c-13.03754,0.14929 -36.52048,-7.13123 -47,-14c-9.53592,-6.25031 -17.73766,-16.23737 -25,-24c-5.79701,-6.19635 -7.84723,-11.04132 -9,-15c-1.76826,-6.07233 -2.31024,-14.03406 -3,-21c-0.50244,-5.07422 -0.59837,-15.37769 4,-22c4.45464,-6.41531 9.32422,-11.8858 12,-20c2.27995,-6.91388 2.39415,-12.01947 2,-16c-0.50244,-5.07422 -2.72398,-9.22272 -6,-13c-3.70638,-4.2735 -9.61383,-9.297 -12,-12c-1.47984,-1.67633 -3.30522,-3.17007 -7,-5c-5.73793,-2.84186 -11,-5 -17,-5c-5,0 -11,0 -17,0c-6,0 -14,1 -20,2c-6,1 -13.06363,3.2674 -22,1c-7.0565,-1.79044 -12.19885,-3.62485 -20,-8c-8.22828,-4.61472 -14.26208,-8.57275 -21,-13c-4.17868,-2.74567 -7.71412,-7.21167 -10,-10c-3.58638,-4.37469 -9.22021,-7.79489 -12,-11c-3.27601,-3.77728 -6.71412,-7.21167 -9,-10c-1.79319,-2.18735 -5.45049,-5.4505 -8,-8c-2.54951,-2.5495 -7,-8 -10,-12c-3,-4 -5.28859,-7.86829 -7,-12c-1.21015,-2.92157 -1.54049,-6.0535 -2,-8c-0.51374,-2.17624 -1,-5 -1,-9c0,-3 0,-7 0,-10c0,-1 -0.30745,-2.186 1,-4c0.8269,-1.14726 2.78985,-3.07843 4,-6c0.85571,-2.06586 4,-2 5,-3c1,-1 3,-2 4,-2c1,0 3,0 4,0c1,0 2,0 3,0c1,0 2,0 3,0c1,0 2,0 3,0c1,0 2.29289,0.70711 3,0c0.70711,-0.70711 0,-2 1,-2c1,0 2,0 2,-1c0,-1 0,-2 0,-3l0,-1l-2,-1"
        private val intersectingPathData = listOf(
            "m489,192c-1,0 -1.70547,-0.34619 -4,-2c-3.62799,-2.6149 -8.22913,-4.68356 -18,-12c-5.12546,-3.83794 -10,-8 -13,-11c-3,-3 -7.34457,-6.74283 -11,-12c-3.3288,-4.7874 -5.49756,-9.9258 -6,-15c-0.59122,-5.97079 -0.41025,-11.44043 6,-17c3.77728,-3.27601 5.04031,-7.64734 8,-11c2.38617,-2.703 5.90778,-4.49622 10,-5c2.97751,-0.36655 8,-1 12,-1c3,0 6,0 10,0c3,0 6.32367,0.52016 8,2c2.703,2.38616 4.07193,5.95327 6,9c3.1181,4.92722 6.20956,9.94349 8,17c2.2674,8.93637 4.47345,16.87001 7,27c1.23395,4.94746 2.49756,9.9258 3,15c0.39417,3.98053 0.703,6.61383 -2,9c-1.67633,1.47984 -4.88153,3.19028 -8,5c-3.86798,2.24469 -6,5 -10,7c-2,1 -3,1 -5,1c-2,0 -4.87857,-1.49345 -8,-2c-0.98709,-0.16019 -2,0 -3,0l-1,-1l-1,-1l-1,-1",
            "m441,512c-1,0 -1.61731,-0.07611 -2,-1c-0.5412,-1.30655 -5,-5 -11,-11c-5,-5 -8.513,-8.59399 -10,-11c-1.66251,-2.69 -3,-6 -7,-12c-2,-3 -4.34619,-6.70547 -6,-9c-2.6149,-3.62799 -4.95517,-6.54916 -6,-10c-0.86935,-2.87128 -1,-6 -1,-9c0,-3 -0.68927,-5.08026 0,-8c0.51373,-2.17624 2.52017,-4.32367 4,-6c2.38617,-2.703 5.31,-2.33749 8,-4c1.203,-0.7435 4,-1 7,-2c3,-1 6,-1 9,-1c2,0 4,0 5,0c2,0 5,1 7,3c2,2 4,4 6,6c2,2 5.34314,4.87604 7,10c1.26855,3.9231 2,7 2,10c0,3 -0.48874,6.02997 0,10c0.50378,4.09222 1,9 1,13c0,3 0,5 0,8c0,3 0,5 0,8c0,2 -1,4 -1,6c0,2 -1.186,2.69254 -3,4c-1.14728,0.8269 -2,1 -5,1c-2,0 -3,0 -5,0c-1,0 -3,0 -5,0c-1,0 -2,0 -3,0l-1,-1l-1,0",
            "m171,389c-1,0 -3,-2 -5,-4c-1,-1 -2,-2 -4,-5c-2,-3 -2,-7 -2,-11c0,-3 0,-7 0,-10c0,-2 -0.4595,-6.0535 0,-8c0.51375,-2.17624 0.33749,-4.31 2,-7c0.7435,-1.203 1,-2 1,-4c0,-1 1,-2 2,-2c1,0 1,-1 3,-3c1,-1 2.186,-1.69254 4,-3c1.14726,-0.8269 3.07613,-2.61731 4,-3c1.30656,-0.5412 2.07613,-0.61731 3,-1c1.30656,-0.5412 0.69344,-2.4588 2,-3c0.92387,-0.38269 2,0 3,-1l1,0",
            "m702,378c1,0 2.21167,0.71411 5,3c3.28101,2.68979 8.40399,6.41156 13,10c2.22937,1.74066 5,4 7,5c2,1 6.06598,2.87766 8,4c3.11847,1.80972 5,3 8,5c3,2 4.87854,3.49347 8,4c0.98706,0.16019 3.87854,1.49347 7,2c1.97418,0.32037 6,0 8,0c2,0 4,0 6,-1l2,0l1,0"
        )
        private val insideNonIntersectingPathData = listOf(
            "m272,326c-1,0 -1.85272,-0.1731 -3,-1c-1.814,-1.30746 -4.86325,-2.14682 -7,-4c-3.77728,-3.276 -9.19577,-5.64886 -13,-8c-4.25325,-2.62866 -8.08743,-4.79395 -13,-7c-4.07967,-1.83203 -8.87856,-4.49347 -12,-5c-2.96126,-0.48056 -5,-1 -8,-1c-3,0 -7,0 -12,-1c-5,-1 -11.07179,-0.69125 -16,-2c-3.05634,-0.81165 -7,-1 -10,-1c-4,0 -8.04132,-0.84723 -12,-2c-3.03616,-0.88412 -6.07843,-1.78986 -9,-3c-2.06586,-0.85571 -3.70546,-2.34619 -6,-4c-5.44197,-3.92236 -12.06601,-6.87766 -14,-8c-3.11848,-1.80972 -4.82443,-2.0979 -6,-4c-0.52573,-0.85065 -1,-2 -2,-3c-2,-2 -3,-3 -5,-5c-1,-1 -3,-2 -3,-3c0,-1 -0.22975,-2.02676 0,-3c0.51374,-2.17624 3.69255,-4.186 5,-6c0.8269,-1.14726 2,-2 2,-3c0,-3 1.19028,-2.88152 3,-6c1.12234,-1.93399 2,-4 4,-5c2,-1 3.70546,-2.34619 6,-4c1.814,-1.30745 4.60643,-2.5387 6,-5c2.03145,-3.58792 2,-6 2,-9c0,-2 -1.11586,-3.96384 -2,-7c-1.15277,-3.95868 -3,-8 -4,-12c-1,-4 -1.87766,-7.06601 -3,-9c-3.61945,-6.23695 -6.49345,-9.87856 -7,-13c-0.32036,-1.97418 0,-4 -1,-5c-1,-1 -3.95517,-3.54915 -5,-7c-0.57957,-1.91418 0,-5 0,-8c0,-2 0,-5 0,-7c0,-1 0,-2 1,-2c2,0 3.71413,1.21167 6,4c3.58638,4.37468 6.81265,7.2068 9,9c5.57664,4.57176 9.56392,6.36774 13,10c2.47778,2.61926 3.70546,6.34619 6,8c1.814,1.30745 4.93414,2.14429 7,3c2.92157,1.21014 5.94699,2.24292 10,3c7.92519,1.48038 14,1 18,1c5,0 10,0 14,-1c4,-1 8,-1 12,-2c4,-1 7.93796,-2.49829 14,-3c6.97615,-0.57736 11.02248,0.36655 14,0c4.09221,-0.50377 7.92578,-2.49756 13,-3c4.97568,-0.49269 8.92578,-0.49756 14,-1c3.98053,-0.39415 8.98453,-0.08075 14,-1c5.98309,-1.0966 10.03873,-2.51945 13,-3c3.12143,-0.50655 6,-3 8,-4c2,-1 3,-2 5,-3c2,-1 3,-2 4,-3c3,-3 4.69254,-5.186 6,-7c0.8269,-1.14726 2,-2 2,-3c0,-1 0.5405,-1.0535 1,-3c0.51373,-2.17625 2.34619,-2.70546 4,-5c1.30746,-1.814 3.4588,-2.69344 4,-4c0.38269,-0.92387 1.14935,-1.52573 2,-1c1.9021,1.17557 1,3 2,5c1,2 2,4 2,7c0,3 0,6 0,9c0,3 -1,5 -1,8c0,2 0.21014,5.07843 -1,8c-0.85571,2.06586 -1,4 -1,8c0,2 2.11215,5.9183 5,10c4.16495,5.8867 8.27805,12.73428 14,19c5.26675,5.76726 9,10 12,13c3,3 6.40402,5.41156 11,9c2.22937,1.74065 4.54916,3.95517 8,5c3.82837,1.15913 8,0 13,0c6,0 11.98877,-0.06982 18,-1c6.98792,-1.08131 14.4176,0.42169 61,-7c17.24673,-2.74782 20,-7 22,-10c2,-3 7.41205,-3.96855 11,-6c2.4613,-1.39357 4,-5 6,-7c3,-3 5.69147,-6.85863 13,-9c5.83734,-1.71033 9,-3 9,-6c0,-3 2.20679,-5.81265 4,-8c2.28589,-2.78833 3.513,-5.59399 5,-8c1.66254,-2.68999 1,-5 1,-8c0,-1 0,-3 0,-4c0,-2 0,-4 1,-5c1,-1 1.61731,-3.07613 2,-4c0.5412,-1.30656 3.01947,-0.60585 7,-1c5.07422,-0.50244 9.92578,-1.50244 15,-1c2.98541,0.29561 6,0 7,0c1,0 3.15222,-0.76537 5,0c1.30658,0.5412 2.18604,1.69255 4,3c1.14728,0.8269 3,3 5,5c1,1 3.513,2.59399 5,5c1.66254,2.68999 3.38507,7.37201 6,11c2.48071,3.4418 5.28333,5.80606 10,13c3.19714,4.8763 3.78986,9.07843 5,12c1.71143,4.13171 3.78986,8.07843 5,11c1.71143,4.13171 3,10 3,15c0,5 -0.14771,8.94341 -2,14c-1.08771,2.96933 -3.49268,6.02434 -3,11c0.50244,5.07422 2,8 2,10c0,3 0.34515,7.17697 2,12c1.45135,4.23007 4.41364,5.62531 8,10c2.28589,2.78833 5.28857,5.86829 7,10c1.21014,2.92157 3,5 5,7c2,2 3,3 4,4c1,1 1.69342,1.4588 3,2c0.92389,0.38269 1.85272,1.1731 3,2c1.81396,1.30746 4,3 6,5c2,2 3,3 5,5c2,2 3.25195,3.88049 6,10c2.20605,4.91257 1,7 1,10c0,3 -1,5 -3,7c-3,3 -4.31,3.33749 -7,5c-2.40601,1.487 -8,3 -23,3c-7,0 -13.966,2.49948 -25,3c-12.98663,0.58908 -15.45807,0.66446 -19,3c-4.49573,2.96448 -10.22272,4.724 -14,8c-3.20514,2.77979 -7.50763,5.63739 -8,11c-0.46619,5.07767 2.3891,9.03748 3,14c0.50378,4.09222 0.67963,6.02582 1,8c0.50653,3.12143 3,6 4,9c1,3 3.98163,7.71201 5,13c0.18909,0.98196 2,4 3,6c1,2 1.48053,5.03873 1,8c-0.50653,3.12143 -2.76953,5.39099 -5,9c-1.66254,2.69 -0.71411,6.21167 -3,9c-1.79321,2.18735 -3.69342,4.4588 -5,5c-1.84778,0.76538 -5.02582,0.32037 -7,0c-3.12146,-0.50653 -6.37201,-2.3851 -10,-5c-4.58905,-3.30762 -10,-9 -14,-13c-4,-4 -6.91754,-8.05664 -9,-11c-2.88788,-4.0817 -6,-7 -9,-10c-3,-3 -5.18604,-5.69254 -7,-7c-3.44183,-2.48071 -6.76111,-5.41589 -10,-7c-2.8407,-1.38934 -7,-4 -11,-5c-4,-1 -10.04352,-2.73209 -17,-4c-9.07013,-1.65314 -19.93796,-4.49829 -26,-5c-5.97955,-0.49487 -17.10699,0.08099 -21,1c-4.35251,1.0275 -8,3 -11,4c-3,1 -7,3 -12,4c-5,1 -10.04132,1.84723 -14,3c-3.03616,0.88412 -8.80902,3.32144 -12,5c-4.76599,2.50705 -10.37201,1.3851 -14,4c-2.29453,1.65381 -6.19577,3.64886 -10,6c-4.25327,2.62866 -7.5582,5.51929 -11,8c-3.62799,2.6149 -7.71899,6.31021 -11,9c-2.78833,2.28589 -5,5 -7,7c-3,3 -7.13202,5.75531 -11,8c-3.11847,1.80972 -4.70547,4.34619 -7,6c-1.814,1.30746 -5.12872,3.13065 -8,4c-3.45084,1.04483 -5.02582,2.67963 -7,3c-3.12143,0.50653 -5.87857,2.49347 -9,3c-1.97418,0.32037 -5.05664,-0.91754 -8,-3c-4.0817,-2.88785 -10.91754,-7.05664 -13,-10c-2.88785,-4.0817 -5.48627,-6.82376 -6,-9c-0.4595,-1.9465 -0.48627,-2.82376 -1,-5c-0.68927,-2.91974 1.16797,-6.92035 3,-11c2.20605,-4.91257 6.51929,-10.5582 9,-14c2.6149,-3.62799 3.64886,-7.19577 6,-11c2.62866,-4.25327 6.22733,-8.78491 10,-13c4.27039,-4.77115 5.90338,-10.01691 7,-16c0.91925,-5.01547 0,-12 -1,-15c-1,-3 -4.49347,-6.87857 -5,-10c-0.48056,-2.96127 -0.69254,-6.186 -2,-8c-0.8269,-1.14728 -3.11215,-2.9183 -6,-7c-2.08246,-2.94336 -2.69254,-5.186 -4,-7c-0.8269,-1.14728 -2.02676,-0.77023 -3,-1c-2.17624,-0.51373 -4,-2 -5,-3c-1,-1 -3.29291,-1.29291 -4,-2c-0.70709,-0.70709 -1,-2 -2,-3c-1,-1 -2.07611,-0.61731 -3,-1c-1.30655,-0.5412 -1,-2 -2,-2c-1,0 -2,0 -3,0l0,-1l1,0",
            "m426,349c-1,0 -2,0 -3,0c-2,0 -3.04132,-0.84723 -7,-2c-3.03616,-0.88412 -6.21167,-1.71411 -9,-4c-2.18735,-1.79318 -4.06601,-3.87766 -6,-5c-3.11847,-1.80972 -5.7695,-4.39099 -8,-8c-1.66251,-2.69 -2,-7 -2,-11c0,-4 -0.43048,-8.133 1,-12c3.10318,-8.3887 6.81403,-12.54895 12,-14c5.85779,-1.63904 12,-2 17,-2c5,0 10.88049,0.25195 17,3c4.91257,2.20605 10.08743,3.79395 15,6c8.15933,3.66406 11,6 13,7c2,1 3.07843,1.78986 6,3c2.06586,0.85571 3.15225,1.23462 5,2c1.30655,0.5412 3,1 4,1c2,0 3,-1 5,-1c1,0 3,0 4,0c1,0 3.07611,-0.38269 4,0c1.30655,0.5412 3.1731,2.85272 4,4c1.30746,1.814 1.48627,3.82376 2,6c0.4595,1.9465 1.49622,4.90778 2,9c0.48874,3.97003 -1.06601,6.87766 -3,8c-3.11847,1.80972 -5.92578,4.49756 -11,5c-9.95132,0.98538 -18,0 -25,0c-7,0 -12,0 -17,0c-5,0 -8,0 -10,0c-2,0 -5.15927,-0.38934 -8,1c-3.23892,1.58411 -4,4 -5,5c-1,1 -1.48627,1.82376 -2,4c-0.22977,0.97324 -2.15225,1.23462 -4,2c-1.30655,0.5412 -2,1 -3,1l-1,0",
        )
        private val outsideNonIntersectingPathData = listOf(
            "m650,49c3,0 6,-1 9,-1c7,0 11,0 14,0c3,0 8.133,0.5695 12,2c4.19434,1.55159 8.22272,3.72399 12,7c4.2735,3.70638 8.367,7.65813 13,13c6.552,7.55454 7.87299,9.45231 12,17c2.58356,4.72495 2.91901,10.107 2,14c-1.02747,4.3525 -4.82288,8.18536 -9,12c-5.76727,5.26675 -11.92224,8.38926 -21,10c-14.90002,2.64383 -27.05042,3.23733 -37,1c-8.04529,-1.80912 -13.34619,-3.70546 -15,-6c-2.61493,-3.62798 -5.29364,-5.72651 -9,-10c-3.276,-3.77727 -5.11212,-6.9183 -8,-11c-2.08246,-2.94335 -2,-9 -2,-12c0,-3 -0.66254,-6.31001 1,-9c1.487,-2.40601 3.34619,-3.70546 5,-6c1.30743,-1.814 2.41888,-3.41886 4,-5c1.58112,-1.58114 1,-3 2,-4c1,-1 2.41888,-2.41886 4,-4c1.58112,-1.58114 5.06567,-0.9685 6,-3c1.72284,-3.7459 -1.06586,-8.14429 1,-9c2.92157,-1.21015 5,-3 8,-4l1,-1l0,-1",
            "m90,458c0,-1 0.45807,-2.66446 4,-5c4.49576,-2.96448 7.38687,-4.9176 10,-6c0.92388,-0.38269 5.22936,-0.66803 9,1c3.29733,1.45865 6.71412,5.21167 9,8c2.68979,3.28101 6.94005,7.37537 10,13c3.02242,5.55563 3.16042,11.96329 4,16c1.23863,5.95532 2.11586,8.96384 3,12c1.15277,3.95868 1.78986,7.07843 3,10c0.85571,2.06586 0.69344,3.4588 2,4c0.92387,0.38269 3.0034,-0.08249 4,0c6.06204,0.50171 11.05165,0.35928 15,1c3.12144,0.50653 5.34619,1.70544 7,4c1.30745,1.81396 3.79488,4.22021 7,7c3.77727,3.276 10.24612,7.62354 15,11c6.36757,4.52264 10,9 15,14c3,3 4.69344,5.4588 6,6c0.92387,0.38269 2.82375,1.48627 5,2c0.97325,0.22974 4,0 6,0c3,0 5.07843,0.21014 8,-1c2.06586,-0.85571 2.69344,-1.4588 4,-2c1.84776,-0.76538 3,0 4,-1l0,-1"
        )
    }

    @Test
    fun testExtendedPathSerialization() {
        val path = ExtendedPath()
        path.moveTo(0f, 0f)
        path.lineTo(100f, 100f)
        val json = Json.encodeToString(path)
        assertEquals(
            """{"commands":[{"type":"MoveTo","x":0.0,"y":0.0},{"type":"LineTo","x":100.0,"y":100.0}]}""",
            json
        )
        val path2 = ExtendedPath.fromJson(json)
        path.addPath(path2)
        val json2 = Json.encodeToString(path)
        assertEquals(
            """{"commands":[{"type":"MoveTo","x":0.0,"y":0.0},{"type":"LineTo","x":100.0,"y":100.0},{"type":"AddPath1","path":{"commands":[{"type":"MoveTo","x":0.0,"y":0.0},{"type":"LineTo","x":100.0,"y":100.0}]}}]}""",
            json2
        )
    }

    @Test
    fun testExtendedPathIntersection() {
        val path = ExtendedPath()
        path.moveTo(0f, 0f)
        path.lineTo(9f, 9f)

        assertTrue(path.doIntersect(3f, 3f))
        assertTrue(path.doIntersect(11f, 11f))
        assertFalse(path.doIntersect(12f, 12f))

        val mainPath = parseExtendedPath(MAIN_PATH_DATA)
        val intersectingPaths = intersectingPathData.map { parseExtendedPath(it) }
        val insideNonIntersectingPaths = insideNonIntersectingPathData.map { parseExtendedPath(it) }
        val outsideNonIntersectingPaths =
            outsideNonIntersectingPathData.map { parseExtendedPath(it) }

        intersectingPaths.forEach {
            assertTrue(mainPath.doIntersect(it, checkInside = CheckInside.NO))
        }
        (insideNonIntersectingPaths + outsideNonIntersectingPaths).forEach {
            assertFalse(mainPath.doIntersect(it, checkInside = CheckInside.NO))
        }
        insideNonIntersectingPaths.forEach {
            assertTrue(mainPath.doIntersect(it, checkInside = CheckInside.YES))
        }
        outsideNonIntersectingPaths.forEach {
            assertFalse(mainPath.doIntersect(it, checkInside = CheckInside.YES))
        }
    }

    private fun parseExtendedPath(pathData: String): ExtendedPath {
        val extendedPath = ExtendedPath()
        val path = PathParser.createPathFromPathData(pathData)
        extendedPath.addPath(path)
        return extendedPath
    }

    @Test
    fun testExtendedPathEquality() {
        val path = ExtendedPath()
        path.moveTo(0f, 0f)
        path.lineTo(9f, 9f)

        val otherPath = ExtendedPath()
        otherPath.moveTo(0f, 0f)
        otherPath.lineTo(9f, 9f)

        assertEquals(path, otherPath)
        assertTrue(path.isEquals(otherPath))

        val anotherPath = ExtendedPath()
        anotherPath.moveTo(0f, 9f)
        anotherPath.lineTo(9f, 0f)

        assertNotEquals(path, anotherPath)
        assertFalse(path.isEquals(anotherPath))
    }

}