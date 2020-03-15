package wordcount;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class WordCountDemo {
    private Set<String> paths = new HashSet<>() ;
    private String condition ="";
    private boolean hasCondition = false;
    private TooltoCount tooltoCount = new TooltoCount();

    public static void main(String[] args) {
        String choose = null ;
        WordCountDemo wordCountDemo = new WordCountDemo();
        Scanner scanner = new Scanner(System.in);
        System.out.println("校验是否执行如下命令行：");
        for (int i = 0;i < args.length;i++){
            System.out.println(args[i]);
        }
        System.out.println("输入Y确定并执行程序，输入N退出程序");
        choose = scanner.nextLine();
        if (choose.charAt(0) == 'Y') {
            wordCountDemo.judge(args);
        }else {
            System.exit(0);
        }
    }

    public String judge(String[] args){
        String count_result = "";
        //取得文件输入流
        paths = tooltoCount.getAllFilePaths(paths);
        for (int i = 0;i < args.length;i++){
            //循环判断输入命令是否为功能
            if(!args[i].startsWith("-") || args[i].endsWith("-s")){
                if (args[i].startsWith("-s")){
                    paths.add(args[i+1]);//如果为-s，收集查询条件
                }
                paths.add(args[i]);//
            }
        }
        for (int j = 0;j < args.length;j++){
            if (args[j].startsWith("-") == true){
                switch (args[j]){
                    case "-c":
                        //-c指令
                        count_result += tooltoCount.countCharacters(paths) + "\r\n";
                        break;
                    case "-w":
                        //-w指令
                        count_result += tooltoCount.countWords(paths) + "\r\n";
                        break;
                    case "-l":
                        //-l指令
                        count_result += tooltoCount.countLines(paths) + "\r\n";
                        break;
                    case "-s":
                        //-s指令
                        if (!args[j+1].startsWith("-")){
                            paths = tooltoCount.getAllFilePaths(paths);
                        }
                        break;
                    case "-a":
                        //-a指令
                        count_result += tooltoCount.countCodeLines(paths) + "\r\n";
                        count_result += tooltoCount.countEmptyLines(paths) + "\r\n";
                        count_result += tooltoCount.countNoteLines(paths) + "\r\n";
                        break;
                    case "-x":
                        //-c指令，还未实现
                        break;
                    default:
                        System.out.println("输入错误！");
                        break;
                }
            }
        }
        System.out.println(count_result);
        return count_result;
    }
}
