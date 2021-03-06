
package mage.cards.d;

import java.util.UUID;
import mage.MageInt;
import mage.MageObject;
import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldAbility;
import mage.abilities.effects.Effect;
import mage.abilities.effects.common.CopyPermanentEffect;
import mage.abilities.keyword.DethroneAbility;
import mage.abilities.keyword.HasteAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.filter.StaticFilters;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.util.functions.ApplyToPermanent;

/**
 *
 * @author LevelX2
 */
public final class DacksDuplicate extends CardImpl {

    public DacksDuplicate(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{2}{U}{R}");
        this.subtype.add(SubType.SHAPESHIFTER);

        this.power = new MageInt(0);
        this.toughness = new MageInt(0);

        // You may have Dack's Duplicate enter the battlefield as a copy of any creature on the battlefield except it gains haste and dethrone.
        Effect effect = new CopyPermanentEffect(StaticFilters.FILTER_PERMANENT_CREATURE, new DacksDuplicateApplyToPermanent());
        effect.setText("as a copy of any creature on the battlefield except it gains haste and dethrone");
        this.addAbility(new EntersBattlefieldAbility(effect, true));
    }

    public DacksDuplicate(final DacksDuplicate card) {
        super(card);
    }

    @Override
    public DacksDuplicate copy() {
        return new DacksDuplicate(this);
    }
}

class DacksDuplicateApplyToPermanent extends ApplyToPermanent {

    @Override
    public boolean apply(Game game, Permanent permanent, Ability source, UUID copyToObjectId) {
        /**
         * 29/05/2014	The ability of Dack’s Duplicate doesn’t target the
         * creature.
         */
        permanent.addAbility(new DethroneAbility(), game);
        permanent.addAbility(HasteAbility.getInstance(), game);
        return true;
    }

    @Override
    public boolean apply(Game game, MageObject mageObject, Ability source, UUID copyToObjectId) {
        mageObject.getAbilities().add(new DethroneAbility());
        mageObject.getAbilities().add(HasteAbility.getInstance());
        return true;
    }

}
