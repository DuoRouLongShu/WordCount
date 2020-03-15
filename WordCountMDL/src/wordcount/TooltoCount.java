package wordcount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class TooltoCount {
    public String countCharacters(Set<String> file_paths) {
        int sum = 0,bytes = 0;
        byte[] t = new byte[30*2048];//存储字符数组
        String count_result = "";
        int length = t.length;//新建length避免循环调用t.length
        FileInputStream in = null;
        try {
            for (String file_path : file_paths) {
                in = new FileInputStream(file_path);
                while ((bytes = in.read(t,0,length))!=-1) {
                    sum+=bytes;//统计字符数
                }
                count_result += file_path + "-字符数：" + sum ;//存储输出的结果
                sum = 0;
            }
            //如果输入有误
        } catch (FileNotFoundException e) {//抛出程序异常，退出程序
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return count_result;
    }

    public String countWords(Set<String> FilePaths){
        String[] Total = {};
        int Count = 0;
        String count_result = "";
        StringBuffer SaveString = new StringBuffer();
        String Tmp = "";
        FileInputStream in = null;
        InputStreamReader isr = null;
        BufferedReader bis = null;
        try {
            for (String FilePath : FilePaths) {
                in = new FileInputStream(FilePath);
                isr = new InputStreamReader(in);
                bis = new BufferedReader(isr);
                while ((Tmp=bis.readLine())!=null) {
                    // 将新读出来的数据接在已保存的后面
                    SaveString.append(Tmp);
                }
                if (SaveString.length()==0) {
                    Count = 0;
                }
                else {
                    // 用字符串存储，以用split方法区分单词
                    Tmp = SaveString.toString();
                    Tmp = Tmp.replaceAll("[^a-zA-Z\\s+]", " ");
                    //读取到的输入文件的数组
                    Total = Tmp.split("[\\s+,\\.\n]");
                    Count = Total.length;
                    // 字符串数组长度就是单词个数
                    // 结果字符串拼接
                    count_result += FilePath+"-单词数："+Count;
                    SaveString.setLength(0);
                    Count = 0;
                }
            }
        } catch (FileNotFoundException e) {
            // 检查到文件不存在，提示错误
            // 结束程序
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                // 关闭文件字符输入流
                in.close();
                // 关闭文件字符输入流
                isr.close();
                // 关闭缓存输入流
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return count_result;
    }

    public String countLines(Set<String> filePaths) {
        int count = 0;
        String result = "";
        // 声明文件字符输入流
        FileInputStream in = null;
        // 声明字节输入流
        InputStreamReader isr = null;
        // 声明缓存输入流
        BufferedReader bis = null;
        try {
            // foreach循环遍历数组
            for (String filePath : filePaths) {

                in = new FileInputStream(filePath);
                // 实例化字节输入流对象
                isr = new InputStreamReader(in);
                // 实例化缓存输入流对象
                bis = new BufferedReader(isr);
                while (bis.readLine()!=null) {
                    count++;
                }
                // 结果字符串拼接
                result += filePath+",行数：" + count;
                count = 0;
            }
        } catch (FileNotFoundException e) {
            // 检查到文件不存在，提示错误
            System.out.println("有文件输入错误，请核对！（如果不会使用相对路径，请使用绝对路径）");
            // 结束程序
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                // 关闭文件字符输入流
                in.close();
                // 关闭字节输入流
                isr.close();
                // 关闭缓存输入流
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return result;
    }

    public String countCodeLines(Set<String> filePaths) {
        // 结果字符串
        String result = "";
        // 缓存变量
        String tmp = "";
        // 统计次数
        int count = 0;
        // 得到输入流
        FileInputStream is = null;
        InputStreamReader ist = null;
        BufferedReader br = null;
        try {
            for (String filePath : filePaths) {
                is = new FileInputStream(filePath);
                ist = new InputStreamReader(is);
                br = new BufferedReader(ist);
                while((tmp = br.readLine())!=null) {
                    // 去除读取的空格，方便识别内容类型
                    tmp = tmp.replace(" ", "");
                    // 改行不为空，则计数+1
                    if (!"".equals(tmp)&&!tmp.startsWith("//")&&tmp.indexOf("//")!=1) {
                        count++;
                    }
                }
                // 拼接结果字符串
                result+=filePath + "-代码行数：" + count;
                // 重置count计数变量
                count = 0;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                // 关闭输入流
                is.close();
                ist.close();
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return result;
    }

    public String countEmptyLines(Set<String> filePaths) {
        // 结果字符串
        String result = "";
        // 缓存变量
        String tmp = "";
        // 统计次数
        int count = 0;
        // 得到输入流
        FileInputStream is = null;
        InputStreamReader ist = null;
        BufferedReader br = null;
        try {
            for (String filePath : filePaths) {
                is = new FileInputStream(filePath);
                ist = new InputStreamReader(is);
                br = new BufferedReader(ist);
                while((tmp = br.readLine())!=null) {
                    // 改行不为空，则计数+1
                    if ("".equals(tmp)) {
                        count++;
                    }
                }
                // 拼接结果字符串
                result+=filePath+ ",空行数：" + count;
                // 重置count计数变量
                count = 0;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                // 关闭输入流
                is.close();
                ist.close();
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return result;
    }

    public String countNoteLines(Set<String> filePaths) {
        // 结果字符串
        String result = "";
        // 缓存变量
        String tmp = "";
        // 统计次数
        int count = 0;
        // 得到输入流
        FileInputStream is = null;
        InputStreamReader ist = null;
        BufferedReader br = null;
        try {
            for (String filePath : filePaths) {
                is = new FileInputStream(filePath);
                ist = new InputStreamReader(is);
                br = new BufferedReader(ist);
                while((tmp = br.readLine())!=null) {
                    // 去除读取的空格，方便识别内容类型
                    tmp = tmp.replace(" ", "");
                    // 改行不为空，则计数+1
                    if (!"".equals(tmp)&&tmp.startsWith("//")||tmp.indexOf("//")==1) {
                        count++;
                    }
                }
                // 拼接结果字符串
                result+=filePath + ",注释行数：" + count;
                // 重置count计数变量
                count = 0;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                // 关闭输入流
                is.close();
                ist.close();
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return result;
    }

    public Set<String> getAllFilePaths(Set<String> names) {
        // 存储所有有效文件路径的集合
        Set<String> filePaths = new HashSet<String>();
        // 先筛选输入文件中的文件夹和有效文件，将有效文件加入集合中
        for (String name : names) {
            File file = new File(name);
            if (file.isFile()) {
                filePaths.add(name);
            }else if (file.isDirectory()) {
                // 如果是文件夹文件，调用专门处理文件夹的方法getDirectoryFiles
                filePaths.addAll(getDirectoryFiles(file));
            }
        }
        return filePaths;
    }

    public static Set<String> getDirectoryFiles(File file) {
        // 文件下有效文件的结果集合
        Set<String> directoryFiles = new HashSet<String>();
        File[] files = file.listFiles();
        for (File file1 : files) {
            if (file1.isFile()) {
                directoryFiles.add(file1.getAbsolutePath());
            }else if (file1.isDirectory()) {
                // 递归调用处理文件夹的方法
                directoryFiles.addAll(getDirectoryFiles(file1));
            }

        }
        return directoryFiles;
    }

    /**//*public void getUI() {
        JFileChooser jFileChooser = new JFileChooser();
        JFrame jFrame = new JFrame("文件数据查询");
        Container container = jFrame.getContentPane();
        container.setLayout(null);

        JLabel hint = new JLabel("选择文件",Label.RIGHT);
        hint.setBounds(0,0,100,30);

        JTextField name = new JTextField();
        name.setBounds(80,0,200, 30);
        name.setEditable(false);
        name.setFont(new Font("dialog", 1, 15));
        name.setBackground(Color.WHITE);

        Button choose = new Button("选择");
        choose.setBounds(300,0,100,30);

        JTextArea contents = new JTextArea();
        contents.setBounds(0,30,300,250);

        choose.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = jFileChooser.showOpenDialog(null);
                if(JFileChooser.APPROVE_OPTION == returnVal) {
                    filePaths.add(jFileChooser.getSelectedFile().getAbsolutePath());
                    name.setText(jFileChooser.getSelectedFile().getName());
                    contents.setText(performOperation());
                }
            }
        });
        container.add(hint);
        container.add(name);
        container.add(choose);
        container.add(contents);
        jFrame.setSize(400, 400);
        jFrame.setVisible(true);
    }*/
}
