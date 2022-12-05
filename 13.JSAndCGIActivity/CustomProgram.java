import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class CustomProgram {
    public CustomProgram(String s) {
    }

    public static void main(String[] args) throws Exception {
        // reads the provided customPage.html into list
        Scanner in = new Scanner(new File("customPage.html"));
        ArrayList<String> list = new ArrayList<>();
        while (in.hasNextLine()) list.add(in.nextLine());


        // update list to reflect changes requested through command line args
        // TODO: Complete this section
        String name_input = null;
        String background = null;
        String greeting = null;
        String suppressOptions = null;
        if (args.length > 0)
            for (String arg : args[0].split("&")) {
                String[] keyValuePair = arg.split("=");
                switch (keyValuePair[0]) {
                    case "name":
                        // TODO: when a greeting is selected (below), this
                        // arguments's value should be displayed in that greeting
                        if(keyValuePair[1]!= null){
                            name_input = keyValuePair[1];
                        }
                        break;
                    case "background":
                        // TODO: when background="Dark", the body's color should be
                        // set to white and it's background-color should be set to
                        // black (the opposite of how they are set for "Light" by
                        // default.
                        if (Objects.equals(keyValuePair[1], "Dark")) {
                            for (String line : list) {
                                background = "true";
                                if (line.contains("background-color: white")) {
                                    line = "background-color: black;";
                                }
                                if (line.contains("color: black")) {
                                    line = "color: white";
                                }
                            }
                        }
                        break;
                    case "Greeting":
                        // TODO: when this argument is present and =true, an <h1>
                        // element containing the text "Welcome Stranger" should
                        // be inserted as the first element within the body.  If
                        // a non-empty-string name is provided (see above), that
                        // name should be used in place of the word Stranger in
                        // this greeting.
                        if (keyValuePair[1] == "true") {
                            greeting = "true";
                            int count = 0;
                            for (String line : list) {
                                if (line.contains("<h1>Page Customization Options:</h1>")) {
                                    break;
                                }
                                count++;
                            }
                            if (name_input != null) {
                                list.add(count, "<h1>Welcome " + name_input + "</h1>");
                            } else {
                                list.add(count, "<h1>Welcome Stranger</h1>");
                            }
                        }
                        break;
                    case "SuppressOptions":
                        // TODO: when this argument is present and =true, the
                        // customization controls should be removed from the page.
                        // Everything from and including the <h1> label through the
                        // final </ul> should be omitted to accomplish this.
                        if(keyValuePair[1] == "true"){
                            suppressOptions = "true";
                            list.removeIf(line -> line.contains("<h1>") || line.contains("<li>") || line.contains("<ul>") || line.contains("</ul>"));
                        }
                        break;
                }
            }

        // print the resulting html out to system.out (standard out)
        for (String line : list)
            System.out.println(line);
        System.out.println(name_input);
        System.out.println(background);
        System.out.println(greeting);
        System.out.println(suppressOptions);
    }
}
