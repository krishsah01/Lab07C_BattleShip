public class ScoreTracker {
    private int missCount;
    private int strikeCount;
    private int totalMisses;
    private int totalHits;

    public void reset() {
        missCount = 0;
        strikeCount = 0;
        totalMisses = 0;
        totalHits = 0;
    }

    public void registerHit() {
        missCount = 0;
        totalHits++;
    }

    public void registerMiss() {
        missCount++;
        totalMisses++;
        if (missCount >= 5) {
            strikeCount++;
            missCount = 0;
        }
    }

    public boolean isStrikeLimitReached() {
        return strikeCount >= 3;
    }

    public int getTotalMisses() {
        return totalMisses;
    }

    public int getTotalHits() {
        return totalHits;
    }

    public int getStrikeCount() {
        return strikeCount;
    }

    public int getMissCount() {
        return missCount;
    }
}