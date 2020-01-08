package com.ray.library.easydialogfragment

import android.os.Parcel
import android.os.Parcelable
import android.view.Gravity
import android.view.ViewGroup

/**
 * @author Ray Huang
 * @since 2020-01-03
 */
class DialogWindowProperty(
    private val width: Int,
    private val height: Int,
    private val gravity: Int,
    private val deltaX: Int,
    private val deltaY: Int,
    private val backgroundResId: Int,
    private val backgroundColor: Int,
    private val animation: Int
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    )

    companion object Factory{

        @JvmField
        val CREATOR = object: Parcelable.Creator<DialogWindowProperty>
        {
            override fun createFromParcel(parcel: Parcel): DialogWindowProperty {
                return DialogWindowProperty(parcel)
            }

            override fun newArray(size: Int): Array<DialogWindowProperty?> {
                return arrayOfNulls(size)
            }
        }

        class Builder(
            private var width: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
            private var height: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
            private var gravity: Int = Gravity.CENTER,
            private var deltaX: Int = 0,
            private var deltaY: Int = 0,
            private var backgroundResId: Int = -1,
            private var backgroundColor: Int = -1,
            private var animation: Int = -1
        ) {
            fun setAnimation(animation: Int): Builder {
                this.animation = animation
                return this
            }

            fun setGravity(gravity: Int): Builder {
                this.gravity = gravity
                return this
            }

            fun setDeltaX(deltaX: Int): Builder {
                this.deltaX = deltaX
                return this
            }

            fun setDeltaY(deltaY: Int): Builder {
                this.deltaY = deltaY
                return this
            }

            fun setWidth(width: Int): Builder {
                this.width = width
                return this
            }

            fun setHeight(height: Int): Builder {
                this.height = height
                return this
            }

            fun setBackgroundResId(backgroundResId: Int): Builder {
                this.backgroundResId = backgroundResId
                return this
            }

            fun setBackgroundColor(backgroundColor: Int): Builder {
                this.backgroundColor = backgroundColor
                return this
            }

            fun build():DialogWindowProperty {
                return DialogWindowProperty(
                    width,
                    height,
                    gravity,
                    deltaX,
                    deltaY,
                    backgroundResId,
                    backgroundColor,
                    animation
                )
            }
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(width)
        parcel.writeInt(height)
        parcel.writeInt(gravity)
        parcel.writeInt(deltaX)
        parcel.writeInt(deltaY)
        parcel.writeInt(backgroundResId)
        parcel.writeInt(backgroundColor)
        parcel.writeInt(animation)
    }

    override fun describeContents(): Int {
        return 0
    }

    /* getter */
    fun getWidth() = width

    fun getHeight() = height

    fun getGravity() = gravity

    fun getDeltaX() = deltaX

    fun getDeltaY() = deltaY

    fun getBackgroundResId() = backgroundResId

    fun getBackgroundColor() = backgroundColor

    fun getAnimation() = animation
}