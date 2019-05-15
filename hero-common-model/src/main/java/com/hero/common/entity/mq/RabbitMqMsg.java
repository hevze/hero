package com.hero.common.entity.mq;

import com.hero.common.entity.stock.BuyHandEntity;
import com.hero.common.entity.stock.HeroMarketEntity;

import java.io.Serializable;

/**
 * MQ数据传送
 *
 * @author Administrator
 */
public class RabbitMqMsg implements Serializable{
    private BuyHandEntity buyHandEntity;

    private HeroMarketEntity marketEntity;

    public BuyHandEntity getBuyHandEntity() {
        return buyHandEntity;
    }

    public void setBuyHandEntity(BuyHandEntity buyHandEntity) {
        this.buyHandEntity = buyHandEntity;
    }

    public HeroMarketEntity getMarketEntity() {
        return marketEntity;
    }

    public void setMarketEntity(HeroMarketEntity marketEntity) {
        this.marketEntity = marketEntity;
    }
}
