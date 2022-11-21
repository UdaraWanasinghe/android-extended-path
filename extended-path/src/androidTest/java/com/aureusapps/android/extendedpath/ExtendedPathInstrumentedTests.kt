package com.aureusapps.android.extendedpath

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    MatrixSerializerInstrumentedTest::class,
    ExtendedPathInstrumentedTest::class
)
class ExtendedPathInstrumentedTests