
package mage.cards.w;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.effects.AsThoughEffectImpl;
import mage.abilities.keyword.DefenderAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.AsThoughEffectType;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.Duration;
import mage.constants.Outcome;
import mage.constants.Zone;
import mage.game.Game;

/**
 *
 * @author LevelX2
 */
public final class WallOfDiffusion extends CardImpl {

    public WallOfDiffusion(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.CREATURE},"{1}{R}");
        this.subtype.add(SubType.WALL);

        this.power = new MageInt(0);
        this.toughness = new MageInt(5);

        // Defender
        this.addAbility(DefenderAbility.getInstance());
        // Wall of Diffusion can block creatures with shadow as though Wall of Diffusion had shadow.
        this.addAbility(new SimpleStaticAbility(Zone.BATTLEFIELD, new CanBlockAsThoughtIthadShadowEffect(Duration.WhileOnBattlefield)));
    }

    public WallOfDiffusion(final WallOfDiffusion card) {
        super(card);
    }

    @Override
    public WallOfDiffusion copy() {
        return new WallOfDiffusion(this);
    }
}

class CanBlockAsThoughtIthadShadowEffect extends AsThoughEffectImpl {

    public CanBlockAsThoughtIthadShadowEffect(Duration duration) {
        super(AsThoughEffectType.BLOCK_SHADOW, duration, Outcome.Benefit);
        staticText = "{this} can block creatures with shadow as though {this} had shadow";
    }

    public CanBlockAsThoughtIthadShadowEffect(final CanBlockAsThoughtIthadShadowEffect effect) {
        super(effect);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        return true;
    }

    @Override
    public CanBlockAsThoughtIthadShadowEffect copy() {
        return new CanBlockAsThoughtIthadShadowEffect(this);
    }

    @Override
    public boolean applies(UUID sourceId, Ability source, UUID affectedControllerId, Game game) {
        return sourceId.equals(source.getSourceId());
    }

}
