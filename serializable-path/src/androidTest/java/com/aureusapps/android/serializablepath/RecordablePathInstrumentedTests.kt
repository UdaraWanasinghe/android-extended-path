package com.aureusapps.android.serializablepath

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    MatrixSerializerInstrumentedTest::class,
    RecordablePathInstrumentedTest::class
)
class RecordablePathInstrumentedTests