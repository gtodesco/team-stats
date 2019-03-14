import { NgModule } from '@angular/core';

import { TeamStatsSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [TeamStatsSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [TeamStatsSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class TeamStatsSharedCommonModule {}
