package com.aureusapps.android.serializablepath

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    MatrixSerializerInstrumentedTest::class,
    SerializablePathInstrumentedTest::class
)
class SerializablePathInstrumentedTests