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
package mage.abilities.condition.common;

import java.util.UUID;
import mage.abilities.Ability;
import mage.abilities.condition.Condition;
import mage.game.Game;


/**
 * As long as the card has remained in the same zone 
 * since this condition was created, the condition is true.
 *
 * @author spjspj
 */
public class SourceHasRemainedInSameZoneCondition implements Condition {

    UUID idToCheck = null;
    int timesChangedZones = -1;
    
    public SourceHasRemainedInSameZoneCondition(UUID idToCheck) {
        this.idToCheck = idToCheck;
        this.timesChangedZones = -1;
    }

    public SourceHasRemainedInSameZoneCondition getInstance(UUID cardId) {
        return new SourceHasRemainedInSameZoneCondition(cardId);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        if (idToCheck != null && game.getCard(idToCheck) != null) {
            if (timesChangedZones == -1) {
                timesChangedZones = game.getState().getZoneChangeCounter(idToCheck);
            }
            if (timesChangedZones == game.getState().getZoneChangeCounter(idToCheck)) {
                return true;
            }
        }
        
        return false;
    }

    @Override
    public String toString() {
        return "if {this} has remained in the same zone";
    }
}