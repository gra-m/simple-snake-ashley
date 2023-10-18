package fun.madeby.util;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.Preferences;

import fun.madeby.snake.component.BodyPartComponent;
import fun.madeby.snake.component.CoinComponent;
import fun.madeby.snake.component.DimensionComponent;
import fun.madeby.snake.component.DirectionComponent;
import fun.madeby.snake.component.MovementComponent;
import fun.madeby.snake.component.PositionComponent;
import fun.madeby.snake.component.RectangularBoundsComponent;
import fun.madeby.snake.component.SnakeComponent;
import fun.madeby.snake.component.TextureComponent;
import fun.madeby.snake.component.ZOrderComponent;

/**
 * Maps for all of the components used in the game, used to retrieve and make use of their contents
 */
public final class Mappers {
    public static final ComponentMapper<RectangularBoundsComponent> RECTANGULAR_BOUNDS_MAPPER =
            ComponentMapper.getFor(RectangularBoundsComponent.class);
    public static final ComponentMapper<DimensionComponent> DIMENSION_COMPONENT_MAPPER =
            ComponentMapper.getFor(DimensionComponent.class);
    public static final ComponentMapper<PositionComponent> POSITION_COMPONENT_MAPPER  =
            ComponentMapper.getFor(PositionComponent.class);
    public static final ComponentMapper<SnakeComponent> SNAKE_COMPONENT_MAPPER =
            ComponentMapper.getFor(SnakeComponent.class);
    public static final ComponentMapper<MovementComponent> MOVEMENT_COMPONENT_MAPPER =
            ComponentMapper.getFor(MovementComponent.class);
    public static final ComponentMapper<DirectionComponent> DIRECTION_COMPONENT_MAPPER =
            ComponentMapper.getFor(DirectionComponent.class);
    public static final ComponentMapper<CoinComponent> COIN_COMPONENT_MAPPER =
            ComponentMapper.getFor(CoinComponent.class);
    public static final ComponentMapper<BodyPartComponent>  BODY_PART_COMPONENT_MAPPER =
            ComponentMapper.getFor(BodyPartComponent.class);
    public static final ComponentMapper<TextureComponent> TEXTURE_COMPONENT_MAPPER =
            ComponentMapper.getFor(TextureComponent.class);
    public static final ComponentMapper<ZOrderComponent> Z_ORDER_COMPONENT_MAPPER =
            ComponentMapper.getFor(ZOrderComponent.class);

    private Mappers(){}
}
