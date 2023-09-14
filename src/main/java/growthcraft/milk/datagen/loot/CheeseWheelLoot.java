package growthcraft.milk.datagen.loot;

import growthcraft.milk.block.BaseCheeseWheel;
import growthcraft.milk.init.GrowthcraftMilkBlocks;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.CopyNbtFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.nbt.ContextNbtProvider;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.Collections;
import java.util.stream.Stream;

public class CheeseWheelLoot extends BlockLootSubProvider {

    protected CheeseWheelLoot() {
        super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        dropSelf(GrowthcraftMilkBlocks.APPENZELLER_CHEESE.get());
        add(GrowthcraftMilkBlocks.AGED_APPENZELLER_CHEESE.get(),
                cheeseBlockDrop(GrowthcraftMilkBlocks.AGED_APPENZELLER_CHEESE.get()));
    }

    private static LootTable.Builder cheeseBlockDrop(Block block) {
        return LootTable.lootTable()
                .withPool(
                        LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(
                                LootItem.lootTableItem(block)
                                .when(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                        .setProperties(
                                                StatePropertiesPredicate.Builder.properties()
                                                .hasProperty(BaseCheeseWheel.SLICE_COUNT_BOTTOM, 0)
                                        )
                                        .invert()
                                )
                                .apply(
                                        CopyNbtFunction
                                        .copyData(ContextNbtProvider.BLOCK_ENTITY)
                                        .copy("slicesbottom", "BlockEntityTag.slicesbottom")
                                )

                        )
                )
                .withPool(
                        LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(
                                LootItem.lootTableItem(block)
                                .when(
                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                        .setProperties(
                                                StatePropertiesPredicate.Builder.properties()
                                                .hasProperty(BaseCheeseWheel.SLICE_COUNT_TOP, 0)
                                        )
                                        .invert()
                                )
                                .apply(
                                        CopyNbtFunction
                                                .copyData(ContextNbtProvider.BLOCK_ENTITY)
                                                .copy("slicestop", "BlockEntityTag.slicesbottom")
                                )
                        )
                );
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return Stream.of(
                GrowthcraftMilkBlocks.AGED_APPENZELLER_CHEESE.get(),
                GrowthcraftMilkBlocks.APPENZELLER_CHEESE.get()
        )::iterator;
//        return Arrays.stream(BaseCheeseWheel.Cheese.values())
//                .flatMap(cheese -> {
//                    Stream<Block> stream = Stream.of(cheese.getUnprocessed(), cheese.getAged());
//                    if (cheese.isWaxable()) {
//                        stream = Stream.concat(stream, Stream.of(cheese.getWaxed()));
//                    }
//                    return stream;
//                })
//                ::iterator;
    }
}
