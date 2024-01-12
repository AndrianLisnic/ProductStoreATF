package context;

import pageObjects.PageObjectManager;
import utils.GenericUtils;
import utils.TestBase;

import java.util.HashMap;
import java.util.Map;

public class TestContextSetup {
    public PageObjectManager pageObjectManager;
    public TestBase testBase;
    public GenericUtils genericUtils;
    private static final Map<Object, Object> data = new HashMap<>();

    public TestContextSetup() throws Exception {
        testBase = new TestBase();
        pageObjectManager = new PageObjectManager(testBase.webDriverManager());
        genericUtils = new GenericUtils(testBase.webDriverManager());
    }

    public void save(Object key, Object value) {
        data.put(key, value);
    }

    public Object getData(Object key) {
        return data.get(key);
    }
}
