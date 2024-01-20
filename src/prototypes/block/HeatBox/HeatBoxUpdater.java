package prototypes.block.HeatBox;

import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.gen.Entityc;
import mindustry.gen.Player;
import mindustry.gen.Syncc;
import mindustry.gen.Timedc;

import java.nio.FloatBuffer;


//TODO maybe use this next time,,,
public class HeatBoxUpdater implements Timedc, Syncc {

    @Override
    public float fin() {
        return 0;
    }

    @Override
    public float lifetime() {
        return 0;
    }

    @Override
    public float time() {
        return 0;
    }

    @Override
    public void lifetime(float v) {

    }

    @Override
    public void time(float v) {

    }

    @Override
    public void update() {

    }

    @Override
    public void write(Writes writes) {

    }

    @Override
    public void updateSpacing(long l) {

    }

    @Override
    public void writeSync(Writes writes) {

    }

    @Override
    public void writeSyncManual(FloatBuffer floatBuffer) {

    }

    @Override
    public void remove() {

    }

    @Override
    public void snapInterpolation() {

    }

    @Override
    public void snapSync() {

    }

    @Override
    public <T extends Entityc> T self() {
        return null;
    }

    @Override
    public <T> T as() {
        return null;
    }

    @Override
    public boolean isAdded() {
        return false;
    }

    @Override
    public boolean isLocal() {
        return false;
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public boolean isRemote() {
        return false;
    }

    @Override
    public boolean serialize() {
        return false;
    }

    @Override
    public int classId() {
        return 0;
    }

    @Override
    public int id() {
        return 0;
    }

    @Override
    public void add() {

    }

    @Override
    public void afterRead() {

    }

    @Override
    public void id(int i) {

    }

    @Override
    public void read(Reads reads) {

    }

    @Override
    public boolean isSyncHidden(Player player) {
        return false;
    }

    @Override
    public long lastUpdated() {
        return 0;
    }

    @Override
    public long updateSpacing() {
        return 0;
    }

    @Override
    public void afterSync() {
    }

    @Override
    public void handleSyncHidden() {

    }

    @Override
    public void interpolate() {

    }

    @Override
    public void lastUpdated(long l) {

    }

    @Override
    public void readSync(Reads reads) {

    }

    @Override
    public void readSyncManual(FloatBuffer floatBuffer) {

    }
}
