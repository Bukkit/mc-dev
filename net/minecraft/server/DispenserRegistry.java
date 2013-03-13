package net.minecraft.server;

public class DispenserRegistry {

    public static void a() {
        BlockDispenser.a.a(Item.ARROW, new DispenseBehaviorArrow());
        BlockDispenser.a.a(Item.EGG, new DispenseBehaviorEgg());
        BlockDispenser.a.a(Item.SNOW_BALL, new DispenseBehaviorSnowBall());
        BlockDispenser.a.a(Item.EXP_BOTTLE, new DispenseBehaviorExpBottle());
        BlockDispenser.a.a(Item.POTION, new DispenseBehaviorPotion());
        BlockDispenser.a.a(Item.MONSTER_EGG, new DispenseBehaviorMonsterEgg());
        BlockDispenser.a.a(Item.FIREWORKS, new DispenseBehaviorFireworks());
        BlockDispenser.a.a(Item.FIREBALL, new DispenseBehaviorFireball());
        BlockDispenser.a.a(Item.BOAT, new DispenseBehaviorBoat());
        DispenseBehaviorFilledBucket dispensebehaviorfilledbucket = new DispenseBehaviorFilledBucket();

        BlockDispenser.a.a(Item.LAVA_BUCKET, dispensebehaviorfilledbucket);
        BlockDispenser.a.a(Item.WATER_BUCKET, dispensebehaviorfilledbucket);
        BlockDispenser.a.a(Item.BUCKET, new DispenseBehaviorEmptyBucket());
        BlockDispenser.a.a(Item.FLINT_AND_STEEL, new DispenseBehaviorFlintAndSteel());
        BlockDispenser.a.a(Item.INK_SACK, new DispenseBehaviorBonemeal());
        BlockDispenser.a.a(Item.byId[Block.TNT.id], new DispenseBehaviorTNT());
    }
}
