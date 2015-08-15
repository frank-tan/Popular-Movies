package com.franktan.popularmovies;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.TestSuiteBuilder;

import junit.framework.Test;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }
    public static Test suite() {
        return new TestSuiteBuilder(ApplicationTest.class)
                .includeAllPackagesUnderHere().build();
    }
}