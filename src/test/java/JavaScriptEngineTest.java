import java.io.File;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import org.junit.Test;

public class JavaScriptEngineTest {

    private String jsDir = "src/test/resources/js";

    /**
     * use https://github.com/walterhiggins/commonjs-modules-javax-script
     * to provide a require() function
     */

    @Test
    public void test() throws ScriptException {
        StringBuilder sb = new StringBuilder();
        sb.append("var Require = load('" + jsDir + "/require.js');");
        sb.append("var require = Require('" + jsDir + "');");

        sb.append("var clientApi = require('/ipportal/ClientApi'); print(clientApi.getClientByCode('ipsoft'));");

        String newScript = sb.toString();
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval(newScript);
    }

    @Test
    public void testFile() {
        File root = new File("");
        System.err.println("root path is " + root.getAbsolutePath());

        String jsDir = "src/test/resources/js";
        File file = new File(jsDir + "/require.js");
        System.err.println(file.getAbsolutePath());
    }
}
