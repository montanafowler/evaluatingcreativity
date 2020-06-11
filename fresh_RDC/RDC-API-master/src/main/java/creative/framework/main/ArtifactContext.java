/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creative.framework.main;

import creative.framework.novelty.Novelty;
import creative.framework.value.Value;

/**
 *
 * @autor creapar team
 * @param <T>
 */
public interface ArtifactContext<T> {

    public Novelty<T> getNoveltyModel();

    public Value<T> getValueModel();

}
