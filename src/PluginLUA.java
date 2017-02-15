import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.luaj.vm2.*;
import org.luaj.vm2.lib.jse.*;
	
public class PluginLUA {
	Server server;
	Globals globals;
	ArrayList<File> luaplugins;
	PluginLUA(Server server) {
		globals = JsePlatform.standardGlobals();
		LuaValue chunk = globals.load("print 'hello, world'");
		chunk.call();
		this.server = server;
		luaplugins = new ArrayList<>();
		server.sw.addLogToConsoleBox("LUA eklentileri araþtýrýlýyor...", 1);
		getLuaPlugins(new File("./plugins/lua/"), luaplugins);
		server.sw.addLogToConsoleBox("LUA eklentileri baþlatýlýyor...", 1);
		try {
			loadLuaPlugins();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void getLuaPlugins(File curDir, ArrayList<File> list) {
		
        File[] filesList = curDir.listFiles();
        for(File f : filesList){
            if(f.isDirectory())
            	getLuaPlugins(f, list);
            if(f.isFile()){
            	if(f.getName().contains(".lua")) {
            		server.sw.addLogToConsoleBox("LUA Eklentisi: "+f.getName(), 1);
            		list.add(f);
            	}
                
            }
        }

    }
	
	private void loadLuaPlugins() throws IOException {
		for (int i = 0; i < luaplugins.size(); i++) {
			File f = luaplugins.get(i);
			FileReader fr = null;
			try {
				fr = new FileReader(f);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				server.sw.addLogToConsoleBox("LUA eklentisi '"+f.getName()+"' yüklenirken bir hata oluþtu!", 0);
			}
			BufferedReader in = new BufferedReader(fr);
			String lv = "";
			String cl;
			while(( cl = in.readLine()) != null) {
				lv = lv + "\n" + cl;
			}
			LuaValue luav = globals.load(lv);
			LuaValue d = globals.get("ssa");
			d.call();
			luav.call("ssa");
			
		}
	}
}
