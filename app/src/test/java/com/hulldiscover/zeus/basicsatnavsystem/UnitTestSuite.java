package com.hulldiscover.zeus.basicsatnavsystem;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Zeus on 12/06/16.
 */

// Runs all unit tests.
@RunWith(Suite.class)
@Suite.SuiteClasses({ExampleUnitTest.class,
        GraphUnitTest.class,
        GraphHasVertex.class})

public class UnitTestSuite {}


