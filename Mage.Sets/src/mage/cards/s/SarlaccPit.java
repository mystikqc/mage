/*
 *  Copyright 2010 BetaSteward_at_googlemail.com. All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without modification, are
 *  permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright notice, this list of
 *        conditions and the following disclaimer.
 *
 *     2. Redistributions in binary form must reproduce the above copyright notice, this list
 *        of conditions and the following disclaimer in the documentation and/or other materials
 *        provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY BetaSteward_at_googlemail.com ``AS IS'' AND ANY EXPRESS OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BetaSteward_at_googlemail.com OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *  ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  The views and conclusions contained in the software and documentation are those of the
 *  authors and should not be interpreted as representing official policies, either expressed
 *  or implied, of BetaSteward_at_googlemail.com.
 */
package mage.cards.s;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.BecomesMonstrousSourceTriggeredAbility;
import mage.abilities.costs.common.SacrificeTargetCost;
import mage.abilities.effects.Effect;
import mage.abilities.effects.common.continuous.GainAbilitySourceEffect;
import mage.abilities.effects.common.continuous.LoseAbilitySourceEffect;
import mage.abilities.keyword.DeathtouchAbility;
import mage.abilities.keyword.DefenderAbility;
import mage.abilities.keyword.FirstStrikeAbility;
import mage.abilities.keyword.HexproofAbility;
import mage.abilities.keyword.MonstrosityAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.filter.common.FilterControlledLandPermanent;
import mage.target.common.TargetControlledPermanent;

/**
 *
 * @author Styxo
 */
public class SarlaccPit extends CardImpl {

    public SarlaccPit(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.CREATURE},"{R}{G}");
        this.subtype.add("Beast");
        this.power = new MageInt(0);
        this.toughness = new MageInt(4);

        // Defender
        this.addAbility(DefenderAbility.getInstance());

        // Hexproof
        this.addAbility(HexproofAbility.getInstance());

        // {R}{G}, Sacrifice a land: Monstrosity 1.
        Ability ability = new MonstrosityAbility("{R}{G}", 1);
        ability.addCost(new SacrificeTargetCost(new TargetControlledPermanent(new FilterControlledLandPermanent("a land"))));
        this.addAbility(ability);

        // When Sarlacc Pit becomes monstrous, it loses hexproof and gains first strike and deathtouch.
        ability = new BecomesMonstrousSourceTriggeredAbility(new LoseAbilitySourceEffect(HexproofAbility.getInstance()));
        Effect effect = new GainAbilitySourceEffect(FirstStrikeAbility.getInstance());
        effect.setText("and gains first strike");
        ability.addEffect(effect);
        effect = new GainAbilitySourceEffect(DeathtouchAbility.getInstance());
        effect.setText("and deathtouch");
        ability.addEffect(effect);

        this.addAbility(ability);
    }

    public SarlaccPit(final SarlaccPit card) {
        super(card);
    }

    @Override
    public SarlaccPit copy() {
        return new SarlaccPit(this);
    }
}