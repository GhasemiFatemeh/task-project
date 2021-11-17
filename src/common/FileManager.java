package common;

import java.io.File;

public class FileManager {
        private static FileManager fileManager= new FileManager();
        private FileManager(){}
        public static FileManager getInstance(){
            return fileManager;
        }

        public String getPath(){
            return (String) RamDB.cachedDB.get("path");
        }

        public void setPath(String path){
            RamDB.cachedDB.put("path",path);
        }

        public String getToken(){
            return (String) RamDB.cachedDB.get("token");
        }

        public void setToken(String token){
            RamDB.cachedDB.put("token",token);
        }

        public boolean isTokenValid(String token){
            return getToken().equals(token);
        }

        public String getFileName(){
            String path= getPath();
            File file =new File(path);
            return file.getName();
        }
}
