
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Lesson4 {
    public static void main(String[] args) throws Exception {
        // ファイルパス（文字コード変更後も同じ）
        String filePath = "C:\\Users\\masha\\Desktop\\コード保存ファイル\\成績一覧表.csv";
        
        // カラム幅を記録するリスト
        List<Integer> columnWidths = new ArrayList<>();
        
        // 最初にファイルを読み込んで、最大カラム幅を計算
        try (FileInputStream fis = new FileInputStream(filePath);
             BufferedReader br = new BufferedReader(new InputStreamReader(fis, "Shift-JIS"))) {
            
            String line;
            while ((line = br.readLine()) != null) {
                String[] wordList = line.split(",", -1);
                
                // 各カラムの幅を計算
                for (int i = 0; i < wordList.length; i++) {
                    int currentWidth = getDisplayWidth(wordList[i]);
                    if (columnWidths.size() <= i) {
                        columnWidths.add(currentWidth);
                    } else {
                        columnWidths.set(i, Math.max(columnWidths.get(i), currentWidth));
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
                    System.out.print(String.format("%-" + columnWidths.get(i) + "s", wordList[i]));
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
