package NewChapter3;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Lesson3 {
    public static void main(String[] args) {
        if (args.length == 1 && args[0].matches("^\\d{8}$")) {
            try {
                int year = Integer.parseInt(args[0].substring(0, 4));
                int month = Integer.parseInt(args[0].substring(4, 6)) - 1; // Calendarの月は0から始まる
                int day = Integer.parseInt(args[0].substring(6, 8));

                Calendar calendar = new GregorianCalendar(year, month, day);

                // 今月の残り週数を表示
                int weeksInMonth = getWeeksInMonth(calendar);
                System.out.println("今月の残り週数: " + weeksInMonth);

                // 年末まで残り週数を表示
                int weeksRemainingInYear = getWeeksRemainingInYear(calendar);
                System.out.println("年末までの残り週数: " + weeksRemainingInYear);

            } catch (NumberFormatException e) {
                System.out.println("無効な数字形式です。");
            } catch (Exception e) {
                System.out.println("エラー: " + e.getMessage());
            }
        } else {
            System.out.println("入力された値が正しくありません。");
        }
    }

    /**
     * 指定したカレンダーの日付が含まれる月の残り週数を計算します。
     */
    private static int getWeeksInMonth(Calendar calendar) {
        Calendar firstDayOfMonth = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        Calendar lastDayOfMonth = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

        // 月の最初の週と最後の週を取得
        int firstWeek = firstDayOfMonth.get(Calendar.WEEK_OF_YEAR);
        int lastWeek = lastDayOfMonth.get(Calendar.WEEK_OF_YEAR);

        // 年をまたぐ場合の調整
        if (lastDayOfMonth.get(Calendar.YEAR) > firstDayOfMonth.get(Calendar.YEAR)) {
            lastWeek = firstDayOfMonth.getWeeksInWeekYear();
        }

        // 週数の計算
        return (lastWeek - firstWeek + 1);
    }

    /**
     * 指定したカレンダーの日付から年末までの週数を計算します。
     */
    private static int getWeeksRemainingInYear(Calendar calendar) {
        Calendar endOfYear = new GregorianCalendar(calendar.get(Calendar.YEAR), Calendar.DECEMBER, 31);

        // 現在の日付と年末の日付の週番号を取得
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        int endOfYearWeek = endOfYear.get(Calendar.WEEK_OF_YEAR);

        // 年をまたぐ場合の調整は不要です
        int totalWeeksInYear = endOfYear.getWeeksInWeekYear();

        // 週数の計算
        // 年末までの残り週数を計算
        int weeksRemaining = (totalWeeksInYear - currentWeek) + 1;

        return weeksRemaining;
    }
}
