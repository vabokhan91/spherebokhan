package com.bokhan.sphere.suite;

import com.bokhan.sphere.creator.SphereCreatorTest;
import com.bokhan.sphere.entity.SphereTest;
import com.bokhan.sphere.parser.SphereParserTest;
import com.bokhan.sphere.reader.ReaderFromFileTest;
import com.bokhan.sphere.storage.SphereStorageTest;
import com.bokhan.sphere.service.SphereParametersCalculatorTest;
import com.bokhan.sphere.service.SphereValidatorTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by vbokh on 29.05.2017.
 */
@Suite.SuiteClasses({
        ReaderFromFileTest.class,
        SphereCreatorTest.class,
        SphereParserTest.class,
        SphereValidatorTest.class,
        SphereStorageTest.class,
        SphereParametersCalculatorTest.class,
        SphereTest.class
})
@RunWith(Suite.class)
public class SuiteTest {

}
