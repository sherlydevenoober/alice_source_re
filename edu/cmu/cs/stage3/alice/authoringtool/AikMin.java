package edu.cmu.cs.stage3.alice.authoringtool;

import java.awt.Font;
import javax.swing.UIManager;

public class AikMin
{
  public static String locale = "";
  public static String defaultLanguage = "English";
  public static String[] listOfLanguages = { "English", "Portuguese", "Spanish", "German" };
  public static int target = 0;
  public static boolean control = false; public static boolean shift = false;
  







  public AikMin() {}
  






  public static boolean isWindows()
  {
    return (System.getProperty("os.name") != null) && (System.getProperty("os.name").toLowerCase().startsWith("win"));
  }
  
  public static boolean isMAC() {
    return (System.getProperty("os.name") != null) && (System.getProperty("os.name").toLowerCase().startsWith("mac"));
  }
  
  public static boolean isUnix() {
    return (System.getProperty("os.name") != null) && (
      (System.getProperty("os.name").toLowerCase().indexOf("nix") >= 0) || 
      (System.getProperty("os.name").toLowerCase().indexOf("nux") >= 0) || (
      System.getProperty("os.name").toLowerCase().indexOf("aix") >= 0));
  }
  
  public static boolean isValidName(String name) {
    if (name == null) {
      return false;
    }
    if (name.length() == 0) {
      return false;
    }
    if (name.trim().length() != name.length()) {
      return false;
    }
    













    CharSequence[] invalidCharacters = {
      "\000", "\001", "\002", "\003", "\004", "\005", "\006", "\007", 
      "\b", "\t", "\\u000A", "\013", "\f", "\\u000D", "\016", "\017", 
      "\020", "\021", "\022", "\023", "\024", "\025", "\026", "\027", 
      "\030", "\031", "\\u001A", "\033", "\034", "\\u001D", "\036", "\037", 
      

















      "\t", "\n", "\\", "/", ":", "*", "?", "\"", "<", ">", "|", "." };
    
    for (int i = 0; i < invalidCharacters.length; i++) {
      if (name.contains(invalidCharacters[i])) {
        return false;
      }
    }
    return true;
  }
  
  public static void setFontSize(int fontSize) {
    Font fontType = UIManager.getFont("Menu.font");
    String name = fontType.getName();
    int style = fontType.getStyle();
    Font font = new Font(name, style, fontSize);
    setUI(font);
  }
  
  private static void setUI(Font font) {
    UIManager.put("Button.font", font);
    UIManager.put("CheckBox.font", font);
    UIManager.put("CheckBoxMenuItem.acceleratorFont", font);
    UIManager.put("CheckBoxMenuItem.font", font);
    UIManager.put("ComboBox.font", font);
    UIManager.put("DesktopIcon.font", font);
    UIManager.put("EditorPane.font", font);
    UIManager.put("FormattedTextField.font", font);
    UIManager.put("InternalFrame.titleFont", font);
    UIManager.put("Label.font", font);
    UIManager.put("List.font", font);
    UIManager.put("Menu.acceleratorFont", font);
    UIManager.put("Menu.font", font);
    UIManager.put("MenuBar.font", font);
    UIManager.put("MenuItem.acceleratorFont", font);
    UIManager.put("MenuItem.font", font);
    UIManager.put("OptionPane.buttonFont", font);
    UIManager.put("OptionPane.messageFont", font);
    UIManager.put("PasswordField.font", font);
    UIManager.put("PopupMenu.font", font);
    UIManager.put("ProgressBar.font", font);
    UIManager.put("RadioButton.font", font);
    UIManager.put("RadioButtonMenuItem.acceleratorFont", font);
    UIManager.put("RadioButtonMenuItem.font", font);
    UIManager.put("Spinner.font", font);
    UIManager.put("TabbedPane.font", font);
    UIManager.put("Table.font", font);
    UIManager.put("TableHeader.font", font);
    UIManager.put("TextArea.font", font);
    UIManager.put("TextField.font", font);
    UIManager.put("TextPane.font", font);
    UIManager.put("TitledBorder.font", font);
    
    UIManager.put("ToolBar.font", font);
    UIManager.put("ToolTip.font", font);
    UIManager.put("Tree.font", font);
    UIManager.put("Viewport.font", font);
    UIManager.put("JTitledPanel.title.font", font);
  }
}
