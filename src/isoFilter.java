
import java.io.File;
import javax.swing.filechooser.FileFilter;

class isoFilter extends FileFilter {

  public boolean accept(File f) {
    if (f.isDirectory())
      return true;
    String s = f.getName();
    int i = s.lastIndexOf('.');

    if (i > 0 && i < s.length() - 1)
      if (s.substring(i + 1).toLowerCase().equals("iso"))
        return true;

    return false;
  }

  
  public String getDescription() {
    return "Only ISO files";
  }
}