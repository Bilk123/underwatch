package com.underwatch.game.level.entities.characters;

import com.badlogic.gdx.graphics.Color;

/**
 * @author Blake
 * <p>
 * The properties which are given to a <code>Hero</code>.
 * @see Hero
 */
public class HeroDef {
    /**
     * All possible teams that a <code>Hero</code> can be apart of.
     */
    public enum Team {

        RED(Color.RED), BLUE(Color.BLUE), BROWN(Color.BROWN), GOLD(Color.GOLD);

        private Color color;

        /**
         * @param color the color used to change the sprite to identify which team the hero is on
         */
        Team(Color color) {
            this.color = color;
        }

        /**
         * @return The color of associated with enumeration
         */
        public Color getColor() {
            return color;
        }
    }
    //modify these in the constructor of child classes

    /**
     * The team the hero is apart of.
     * changes some graphical features of the hero's sprite
     **/
    Team team = Team.RED;

    /**
     * The health of the hero.
     **/
    int hitPoints = 100;

    /**
     * How many bullets/shells the hero has.
     **/
    int ammo = 20;

    /**
     * This is how many bullets are shot every time the hero shoots.
     **/
    int rayCastsPerShot = 1;

    /**
     * The time it takes the player to reload in seconds.
     **/
    float reloadTime = 1f;

    /**
     * This is how many hitpoints another hero would lose per raycast hit.
     **/
    int damage = 10;

    /**
     * The horizontal speed the player moves in meters/second.
     **/
    float speed = 7f;

    /**
     * The max height the hero can jump in meters.
     **/
    float jumpHeight = 2.5f;

    /**
     * The armour the hero has which will reduce the amount of <code>damage</code> taken when damage is dealt by another player.
     * Decreases as more damage is taken.
     **/
    int armour = 0;

    /**
     * The idle crosshair radius in pixels
     **/
    float minCrosshairRadius = 10;

    /**
     * The maximum crosshair radius in pixels set equal to <code>minCrosshairRadius</code> to disable blooming
     **/
    float maxCrosshairRadius = 20;
}
