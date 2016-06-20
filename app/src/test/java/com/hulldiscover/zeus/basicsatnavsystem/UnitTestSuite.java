package com.hulldiscover.zeus.basicsatnavsystem;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Zeus on 12/06/16.
 */

// Runs all unit tests.
@RunWith(Suite.class)
@Suite.SuiteClasses({
        DirectedGraphUnitTest.class,
        GraphHasVertex.class,
        PathFinder.class,
        RouteLengthTest.class,
        ShortestPathTest.class})

public class UnitTestSuite {}


