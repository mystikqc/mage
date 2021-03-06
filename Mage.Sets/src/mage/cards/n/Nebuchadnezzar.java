
package mage.cards.n;

import java.util.UUID;
import mage.MageInt;
import mage.MageObject;
import mage.abilities.Ability;
import mage.abilities.common.ActivateIfConditionActivatedAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.condition.common.MyTurnCondition;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.NameACardEffect;
import mage.cards.*;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.constants.Duration;
import mage.constants.Outcome;
import mage.constants.SuperType;
import mage.constants.Zone;
import mage.game.Game;
import mage.players.Player;
import mage.target.common.TargetOpponent;

/**
 *
 * @author fireshoes & L_J
 */
public final class Nebuchadnezzar extends CardImpl {

    public Nebuchadnezzar(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{3}{U}{B}");
        addSuperType(SuperType.LEGENDARY);
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.WIZARD);
        this.power = new MageInt(3);
        this.toughness = new MageInt(3);

        // {X}, {T}: Choose a card name. Target opponent reveals X cards at random from their hand. Then that player discards all cards with that name revealed this way. Activate this ability only during your turn.
        Ability ability = new ActivateIfConditionActivatedAbility(Zone.BATTLEFIELD, new NameACardEffect(NameACardEffect.TypeOfName.ALL), new ManaCostsImpl("{X}"), MyTurnCondition.instance);
        ability.addCost(new TapSourceCost());
        ability.addEffect(new NebuchadnezzarEffect());
        ability.addTarget(new TargetOpponent());
        this.addAbility(ability);
    }

    public Nebuchadnezzar(final Nebuchadnezzar card) {
        super(card);
    }

    @Override
    public Nebuchadnezzar copy() {
        return new Nebuchadnezzar(this);
    }
}

class NebuchadnezzarEffect extends OneShotEffect {

    public NebuchadnezzarEffect() {
        super(Outcome.Detriment);
        staticText = "Target opponent reveals X cards at random from their hand. Then that player discards all cards with that name revealed this way";
    }

    public NebuchadnezzarEffect(final NebuchadnezzarEffect effect) {
        super(effect);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player opponent = game.getPlayer(targetPointer.getFirst(game, source));
        MageObject sourceObject = game.getObject(source.getSourceId());
        String cardName = (String) game.getState().getValue(source.getSourceId().toString() + NameACardEffect.INFO_KEY);
        if (opponent != null && sourceObject != null && !cardName.isEmpty()) {
            int costX = source.getManaCostsToPay().getX();
            if (costX > 0 && !opponent.getHand().isEmpty()) {
                Cards cards = new CardsImpl();
                while (costX > 0) {
                    Card card = opponent.getHand().getRandom(game);
                    if (!cards.contains(card.getId())) {
                        cards.add(card);
                        costX--;
                    }
                    if (opponent.getHand().size() <= cards.size()) {
                        break;
                    }
                }
                opponent.revealCards(sourceObject.getIdName(), cards, game);
                for (Card cardToDiscard : cards.getCards(game)) {
                    if (cardToDiscard.getName().equals(cardName)) {
                        opponent.discard(cardToDiscard, source, game);
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public NebuchadnezzarEffect copy() {
        return new NebuchadnezzarEffect(this);
    }
}
