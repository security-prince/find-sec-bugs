/**
 * Find Security Bugs
 * Copyright (c) 2014, Philippe Arteau, All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library.
 */
package com.h3xstream.findsecbugs.password;

import com.h3xstream.findbugs.test.BaseDetectorTest;
import com.h3xstream.findbugs.test.EasyBugReporter;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class JndiCredentialsDetectorTest extends BaseDetectorTest {

    @Test
    public void detectHardCodeCredentials() throws Exception {
        //Locate test code
        String[] files = {
                getClassFilePath("testcode/password/JndiProperties")
        };

        //Run the analysis
        EasyBugReporter reporter = spy(new EasyBugReporter());
        analyze(files, reporter);

        //Assertions
        for (Integer line : Arrays.asList(10, 15)) {
            verify(reporter).doReportBug(
                    bugDefinition()
                            .bugType("HARD_CODE_PASSWORD")
                            .inClass("JndiProperties").atLine(line)
                            .build()
            );
        }

        //More than two occurrence == false positive
        verify(reporter, times(2)).doReportBug(
                bugDefinition().bugType("HARD_CODE_PASSWORD").build());
    }
}
