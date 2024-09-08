
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Lesson4 {
    public static void main(String[] args) throws Exception {
        int columnMaxWidth = 0;
        String tabWidth = " ";
        // ファイルパス（文字コード変更後も同じ）
        String filePath = "C:\\Users\\masha\\Desktop\\コード保存ファイル\\成績一覧表.csv";
        
        // カラム幅を記録するリスト
        List<Integer> columnWidths = new ArrayList<>();
        
        // 最初にファイルを読み込んで、最大カラム幅を計算
        try (FileInputStream fis = new FileInputStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis, "Shift-JIS"))) {
            
            String line;
            while((line = br.readLine()) != null){
                String[] wordList = line.split(",", -1);
                for(int i = 0;i < wordList.length; i++){
                    if(columnMaxWidth < getDisplayWidth(wordList[i])){
                        columnMaxWidth = getDisplayWidth(wordList[i]);
                    }
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // ファイルを再度読み込んで、フォーマット済みの内容を出力
        try (FileInputStream fis = new FileInputStream(filePath);
             BufferedReader br = new BufferedReader(new InputStreamReader(fis, "Shift-JIS"))) {
            
            String line;
            while ((line = br.readLine()) != null) {
                String[] wordList = line.split(",", -1);
                
                // カラムごとに幅に合わせて整形して出力
                for (int i = 0; i < wordList.length; i++) {
                    // 各カラムにカラム幅に基づいた整形を適用
                    if(wordList[i].isEmpty()){
                        wordList[i] = "未設定";
                    }
                    for(int j = 0; j <  (columnMaxWidth - getDisplayWidth(wordList[i])); j++){
                        tabWidth += " ";
                    }
                    System.out.print(wordList[i] + tabWidth);
                    tabWidth = " ";
                }
                System.out.println();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    // 文字列の表示幅を計算するメソッド
    private static int getDisplayWidth(String text) {
        int width = 0;
        for (char c : text.toCharArray()) {
            if (Character.toString(c).getBytes().length == 1) {
                // 半角文字の場合
                width += 1;
            } else {
                // 全角文字の場合
                width += 2;
            }
        }
        return width;
    }
}
