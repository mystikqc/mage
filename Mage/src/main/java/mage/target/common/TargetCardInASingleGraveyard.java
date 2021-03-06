

package mage.target.common;

import java.util.UUID;
import mage.abilities.Ability;
import mage.cards.Card;
import mage.constants.Zone;
import mage.filter.FilterCard;
import mage.game.Game;
import mage.target.TargetCard;


/**
 *
 * @author LevelX2
 */


public class TargetCardInASingleGraveyard extends TargetCard {

    public TargetCardInASingleGraveyard(int minNumTargets, int maxNumTargets, FilterCard filter) {
        super(minNumTargets, maxNumTargets, Zone.GRAVEYARD, filter);
    }

    public TargetCardInASingleGraveyard(final TargetCardInASingleGraveyard target) {
        super(target);
    }

    @Override
    public boolean canTarget(UUID id, Ability source, Game game) {
        UUID firstTarget = this.getFirstTarget();
        if (firstTarget != null) {
            Card card = game.getCard(firstTarget);
            Card targetCard = game.getCard(id);
            if (card == null || targetCard == null
                    || !card.getOwnerId().equals(targetCard.getOwnerId())) {
                return false;
            }
        }
        return super.canTarget(id, source, game);
    }


    @Override
    public TargetCardInASingleGraveyard copy() {
        return new TargetCardInASingleGraveyard(this);
    }
}
