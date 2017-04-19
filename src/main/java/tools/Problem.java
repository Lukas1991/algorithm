package tools;

public class Problem {
    int number;
    Difficulty difficulty;
    String title;

    public Problem(String[] arr) {
        this.number = Integer.valueOf(arr[0]);
        this.difficulty = Difficulty.valueOf(arr[3]);
        this.title = arr[1];
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return number +
            "\t" + title + '\'' +
            "\t" + difficulty;
    }

    public enum Difficulty {Easy, Medium, Hard}
}
