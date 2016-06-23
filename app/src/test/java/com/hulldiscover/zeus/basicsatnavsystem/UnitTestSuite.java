package com.hulldiscover.zeus.basicsatnavsystem;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Zeus on 12/06/16.
 *
 * Test Suite to run all test.
 */

// Runs all unit tests.
@RunWith(Suite.class)
@Suite.SuiteClasses({
        DirectedGraphUnitTest.class,
        GraphHasVertex.class,
        PathFinderTest.class,
        RouteLengthTest.class,
        ShortestPathTest.class})

public class UnitTestSuite {}


