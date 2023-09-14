package growthcraft.milk.datagen.loot;

import growthcraft.milk.block.BaseCheeseWheel;
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
import org.jetbrains.annotations.NotNull;

import java.util.Collections;

public class CheeseWheelLoot extends BlockLootSubProvider {

    protected CheeseWheelLoot() {
        super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        BaseCheeseWheel.Cheese.allCheeses().forEach(
                cheese -> add(cheese, cheeseBlockDrop(cheese))
        );
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
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return BaseCheeseWheel.Cheese.allCheeses()::iterator;
    }
}
