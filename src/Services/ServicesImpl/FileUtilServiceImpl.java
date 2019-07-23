package Services.ServicesImpl;

import Services.IFileUtilService;

import java.io.File;

public class FileUtilServiceImpl implements IFileUtilService {
    @Override
    public boolean deleteFile(String filePath) {
        File file = new File(filePath);
            if (!file.exists() || file.isDirectory()) {
                return false;
            }

            return file.delete();
        }
    }

