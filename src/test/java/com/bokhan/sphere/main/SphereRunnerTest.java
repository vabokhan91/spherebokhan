package com.bokhan.sphere.main;

import com.bokhan.sphere.creator.SphereCreatorTest;
import com.bokhan.sphere.entity.SphereTest;
import com.bokhan.sphere.parser.SphereParserTest;
import com.bokhan.sphere.reader.ReaderFromFileTest;
import com.bokhan.sphere.storage.SphereStorageTest;
import com.bokhan.sphere.util.SphereParametersCalculatorTest;
import com.bokhan.sphere.util.SphereValidatorTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by vbokh on 29.05.2017.
 */
@Suite.SuiteClasses({SphereStorageTest.class,ReaderFromFileTest.class,
        SphereCreatorTest.class,SphereParserTest.class, SphereValidatorTest.class, SphereTest.class, SphereParametersCalculatorTest.class,
        })
@RunWith(Suite.class)
public class SphereRunnerTest {

}
