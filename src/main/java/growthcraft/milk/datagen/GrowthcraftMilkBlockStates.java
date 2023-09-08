package growthcraft.milk.datagen;

import growthcraft.milk.init.GrowthcraftMilkBlocks;
import growthcraft.milk.shared.Reference;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.MultiPartBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.ArrayList;
import java.util.List;

import static growthcraft.milk.block.CheeseWheelBlock.SLICE_COUNT_BOTTOM;
import static growthcraft.milk.block.CheeseWheelBlock.SLICE_COUNT_TOP;

public class GrowthcraftMilkBlockStates extends BlockStateProvider {

    private final ModelFile empty_model = models().getExistingFile(new ResourceLocation("growthcraft", "block/empty"));

    public GrowthcraftMilkBlockStates(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Reference.MODID, exFileHelper);
    }

    // templates
    private final List<ResourceLocation> lower = List.of(
            modLoc("block/cheese_wheel/cheese_slab_slices_bottom_1"),
            modLoc("block/cheese_wheel/cheese_slab_slices_bottom_2"),
            modLoc("block/cheese_wheel/cheese_slab_slices_bottom_3"),
            modLoc("block/cheese_wheel/cheese_slab_slices_bottom_4")
    );
    private final List<ResourceLocation> upper = List.of(
            modLoc("block/cheese_wheel/cheese_slab_slices_top_1"),
            modLoc("block/cheese_wheel/cheese_slab_slices_top_2"),
            modLoc("block/cheese_wheel/cheese_slab_slices_top_3"),
            modLoc("block/cheese_wheel/cheese_slab_slices_top_4")
    );

    private final ResourceLocation emptyModel = new ResourceLocation("growthcraft", "block/empty");

    @Override
    protected void registerStatesAndModels() {
        aged_cheese("appenzeller");
        aged_cheese("asiago");
        aged_cheese("casu_marzu");
        waxed_cheese("cheddar");
        aged_cheese("emmentaler");
        aged_cheese("gorgonzola");
        waxed_cheese("gouda");
        waxed_cheese("monterey");
        aged_cheese("parmesan");
        //waxed_cheese("provolone");

        for (int i = 0; i < lower.size(); i++) {
            models().withExistingParent(
                            "block/cheese_wheel/provolone_waxed_lower_" + (i+1), lower.get(i))
                    .texture("0", "block/cheese/provolone_waxed_top")
                    .texture("1", "block/cheese/provolone_waxed_side");

        } for (int i = 0; i < upper.size(); i++) {
            models().withExistingParent(
                    "block/cheese_wheel/provolone_waxed_upper_" + (i + 1), upper.get(i))
                    .texture("0", "block/cheese/provolone_waxed_top")
                    .texture("1", "block/cheese/provolone_waxed_side");
        }

        horizontalBlock(
                GrowthcraftMilkBlocks.WAXED_PROVOLONE_CHEESE.get(),
                state -> {
                    if (state.getValue(SLICE_COUNT_TOP) == 0 && state.getValue(SLICE_COUNT_BOTTOM) == 0) {
                        return empty_model;
                    } else if (state.getValue(SLICE_COUNT_TOP) == 0) {
                        return models().getExistingFile(modLoc("block/cheese_wheel/provolone_waxed_lower_" + state.getValue(SLICE_COUNT_BOTTOM)));
                    } else {
                        return models().getExistingFile(modLoc("block/cheese_wheel/provolone_waxed_upper_" + state.getValue(SLICE_COUNT_TOP)));
                    }
                }
        );

        simpleBlockItem(GrowthcraftMilkBlocks.WAXED_PROVOLONE_CHEESE.get(),
                models().getExistingFile(modLoc("block/cheese_wheel/provolone_waxed_lower_4")));
    }




    /**
     * Generates the blockstate file for a cheese using a multipart file for separate control over the top and bottom
     * wheel of cheese
     * @param lowerModels List of 4 ModelFiles for each stage of the lower wheel of cheese
     * @param upperModels List of 4 Modelfiles for each stage of the upper wheel of cheese
     */
    private void generateMultipartCheese(List<ModelFile> lowerModels, List<ModelFile> upperModels) {
        MultiPartBlockStateBuilder builder = getMultipartBuilder(GrowthcraftMilkBlocks.WAXED_PROVOLONE_CHEESE.get());

        builder.part()
                .modelFile(models().getExistingFile(emptyModel))
                .addModel()
                .useOr()
                .condition(SLICE_COUNT_TOP, 0)
                .condition(SLICE_COUNT_BOTTOM, 0)
                .end();

        for (Direction direction : Direction.Plane.HORIZONTAL) {
            for (ModelFile model : lowerModels) {
                builder.part()
                        .modelFile(model)
                        .rotationY((int) direction.toYRot())
                        .addModel()
                        .condition(BlockStateProperties.HORIZONTAL_FACING, direction)
                        .condition(SLICE_COUNT_BOTTOM, lowerModels.indexOf(model) + 1)
                        .end();
            }
            for (ModelFile model : upperModels) {
                builder.part()
                        .modelFile(model)
                        .rotationY((int) direction.toYRot())
                        .addModel()
                        .condition(BlockStateProperties.HORIZONTAL_FACING, direction)
                        .condition(SLICE_COUNT_TOP, upperModels.indexOf(model) + 1)
                        .end();
            }
        }
    }


    private void aged_cheese(String modelName) {
        textured_cheese(modelName + "_unaged", modelName + "_unaged_side", modelName + "_unaged_top");
        textured_cheese(modelName + "_aged", modelName + "_aged_side", modelName + "_aged_top");
    }

    private void waxed_cheese(String modelName) {
        textured_cheese(modelName + "_unwaxed", modelName + "_unwaxed_side", modelName + "_unwaxed_top");
        textured_cheese(modelName + "_waxed", modelName + "_waxed_side", modelName + "_waxed_top");
    }



    private void textured_cheese(String modelName, String sideTexture, String topTexture) {
        for (int i = 0; i < lower.size(); i++) {
            models().withExistingParent(
                    "block/cheese_wheel/" + modelName + "_lower_" + (i+1), lower.get(i))
                    .texture("0", "block/cheese/" + topTexture)
                    .texture("1", "block/cheese/" + sideTexture);

        } for (int i = 0; i < upper.size(); i++) {
            models().withExistingParent("block/cheese_wheel/" + modelName + "_upper_" + (i+1), upper.get(i))
                    .texture("0", "block/cheese/" + topTexture)
                    .texture("1", "block/cheese/" + sideTexture);
        }
    }
}
