package io.github.nyliumpowered.nylium.util

import net.minecraft.entity.Entity
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.BlockRotation
import net.minecraft.util.Identifier
import net.minecraft.util.math.Direction
import net.minecraft.util.math.Vec2f
import net.minecraft.util.math.Vec3d
import net.minecraft.util.math.Vec3i
import net.minecraft.util.registry.RegistryKey
import net.minecraft.world.World

/**
 * Represents a Location inside of a dimension with a rotation.
 */
data class Location(
        val pos: Vec3d,
        val rot: Vec2f?,
        val dim: Identifier
) {
    constructor(
            position: Vec3d,
            dimension: Identifier
    ) : this(position, null, dimension)

    constructor(
            position: Vec3d,
            world: World
    ) : this(position, null, world.registryKey.value)

    constructor(
            position: Vec3d,
            rotation: Vec2f?,
            world: World
    ) : this(position, rotation, world.registryKey.value)

    companion object {
        @JvmField
        val ORIGIN: Location = Location(Vec3d.ZERO, Vec2f.ZERO, World.OVERWORLD.value)

        @JvmStatic
        fun of(entity: Entity) = Location(entity.pos, Vec2f(entity.yaw, entity.pitch), entity.world.registryKey.value)
    }

    /**
     * Gets the ServerWorld this Location is in
     */
    val world: ServerWorld
        get() = TODO() /*Nylium.instance.server.base.getWorld(RegistryKey.of(Registry.DIMENSION, dimension))*/

    val worldOrNull: ServerWorld?
        get() = TODO()

    /**
     * Moves the position up by the specified amount.
     * @param distance the specified amount
     */
    fun up(distance: Int = 1) = offset(distance, Direction.UP)

    /**
     * Moves the position down by the specified amount.
     * @param distance the specified amount
     */
    fun down(distance: Int = 1) = offset(distance, Direction.DOWN)

    /**
     * Moves the position towards the north direction by the specified amount.
     * @param distance the specified amount
     */
    fun north(distance: Int = 1) = offset(distance, Direction.NORTH)

    /**
     * Moves the position towards the south direction by the specified amount.
     * @param distance the specified amount
     */
    fun south(distance: Int = 1) = offset(distance, Direction.SOUTH)

    /**
     * Moves the position towards the west direction by the specified amount.
     * @param distance the specified amount
     */
    fun west(distance: Int = 1) = offset(distance, Direction.WEST)

    /**
     * Moves the position towards the east direction by the specified amount.
     * @param distance the specified amount
     */
    fun east(distance: Int = 1) = offset(distance, Direction.EAST)

    /**
     * Offsets the position in the specified direction by the specified amount.
     * @param distance the specified amount
     * @param direction the specified direction
     */
    fun offset(distance: Int = 1, direction: Direction) = if (distance == 0) this else Location(
            Vec3d(
                    pos.x + direction.offsetX * distance,
                    pos.y + direction.offsetY * distance,
                    pos.z + direction.offsetZ * distance
            ), rot, dim
    )

    /**
     * Rotates the position align the axis by the specified rotation.
     * @param rotation the specified block rotation
     */
    fun axisAlignedRotate(rotation: BlockRotation) = when (rotation) {
        BlockRotation.CLOCKWISE_90 -> {
            Location(Vec3d(-pos.z, pos.y, pos.x), this.rot, dim)
        }
        BlockRotation.CLOCKWISE_180 -> {
            Location(Vec3d(-pos.x, pos.y, -pos.z), this.rot, dim)
        }
        BlockRotation.COUNTERCLOCKWISE_90 -> {
            Location(Vec3d(pos.z, pos.y, -pos.x), this.rot, dim)
        }
        else -> this
    }

    /**
     * Returns a new Location with the rotation provided and the position and dimension of this Location.
     * @param yaw the Yaw rotation
     * @param pitch the Pitch rotation
     */
    fun withRotation(yaw: Float, pitch: Float) = withRotation(Vec2f(yaw, pitch))

    /**
     * Returns a new Location with the rotation provided and the position and dimension of this Location.
     * @param vector the Yaw and Pitch rotations
     */
    fun withRotation(vector: Vec2f) = if (vector == rot) this else Location(pos, vector, dim)

    /**
     * Returns a new Location with the dimension provided and the position and rotation of this Location.
     * @param world the world to get the dimension from
     */
    fun withDimension(world: World) = withDimension(world.registryKey.value)

    /**
     * Returns a new Location with the dimension provided and the position and rotation of this Location.
     * @param dimension the Identifier of the dimension
     */
    fun withDimension(dimension: Identifier) = if (dimension == this.dim) this else Location(pos, rot, dimension)

    /**
     * Returns a new Location with the dimension provided and the position and rotation of this Location.
     * @param key the registry key of the dimension
     */
    fun withDimension(key: RegistryKey<World>) = withDimension(key.value)

    /**
     * Returns a new Location with the dimension provided and the position and rotation of this Location.
     * @param x the X coordinate
     * @param y the Y coordinate
     * @param z the Z coordinate
     */
    fun withPosition(x: Double = this.x, y: Double = this.y, z: Double = this.z) = withPosition(Vec3d(x, y, z))

    /**
     * Returns a new Location with the dimension provided and the position and rotation of this Location.
     * @param vector The position to set
     */
    fun withPosition(vector: Vec3d) = if (vector == pos) this else Location(vector, rot, dim)
    fun withPosition(vector: Vec3i) = withPosition(Vec3d(vector.x.toDouble(), vector.y.toDouble(), vector.z.toDouble()))

    inline val x: Double
        get() = pos.x

    inline val y: Double
        get() = pos.y

    inline val z: Double
        get() = pos.z

    inline val yaw: Float?
        get() = rot?.x

    inline val pitch: Float?
        get() = rot?.y
}