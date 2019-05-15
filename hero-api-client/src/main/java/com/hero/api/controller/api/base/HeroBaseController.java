package com.hero.api.controller.api.base;

import com.hero.index.facade.service.HeroIndexFacade;
import com.hero.market.facade.service.HeroMarketFacade;
import com.hero.my.facade.service.HeroMyFacade;
import com.hero.shelfselect.facade.service.HeroShelfSelectFacade;
import com.hero.trade.facade.service.HeroTradeFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类说明 ：
 *
 * @ClassName HeroBaseController
 * @Author hwz.hs
 * @Date 2019/5/11 18:10
 * @Version v_1.0
 */
@RestController
@EnableAutoConfiguration
public class HeroBaseController {

    @Autowired
    private HeroIndexFacade heroIndexFacade;
   @Autowired
    private HeroTradeFacade heroTradeFacade;
    @Autowired
    private HeroMyFacade heroMyFacade;
    @Autowired
    private HeroMarketFacade heroMarketFacade;
    @Autowired
    private HeroShelfSelectFacade heroShelfSelectFacade;

}
