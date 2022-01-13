public class Tests {
    public static void main (String[] args) {
        assert Program.AddInt(1,2) == 3 : "Testing AddInt failed";
        assert Program.AddInt(5,6) == 11 : "Testing AddIntWrong failed";
        assert Program.SubtractInt(8,4) == 4 : "Testing SubtractInt failed";
        assert Program.SubtractInt(12,21) == -9 : "Testing SubtractInt failed";
    }
}