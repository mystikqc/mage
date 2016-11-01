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
package mage.cards.w;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import mage.constants.CardType;
import mage.abilities.effects.common.DamageTargetEffect;
import mage.abilities.effects.common.GainLifeEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.target.common.TargetCreatureOrPlayer;

/**
 *
 * @author jeffwadsworth
 */
public class WarleadersHelix extends CardImpl {

    public WarleadersHelix(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.INSTANT},"{2}{R}{W}");


        // Warleader's Helix deals 4 damage to target creature or player and you gain 4 life.
        this.getSpellAbility().addEffect(new DamageTargetEffect(4));
        this.getSpellAbility().addEffect(new GainLifeEffect(4));
        this.getSpellAbility().addTarget(new TargetCreatureOrPlayer());
    }

    public WarleadersHelix(final WarleadersHelix card) {
        super(card);
    }

    @Override
    public WarleadersHelix copy() {
        return new WarleadersHelix(this);
    }
    
    @Override
    public List<String> getRules() {
        List<String> rules = new ArrayList<String>();
        rules.add("Warleader's Helix deals 4 damage to target creature or player and you gain 4 life.");
        return rules;
    }
}