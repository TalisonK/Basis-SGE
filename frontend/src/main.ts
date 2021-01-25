import { enableProdMode } from '@angular/core';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { AppModule } from './app/app.module';
import { environment } from './environments/environment';
import { bootstrapSecurity } from '@nuvem/angular-base';

if (environment.production) {
    enableProdMode();
}

bootstrapSecurity(environment.auth, () => {
    platformBrowserDynamic()
        .bootstrapModule(AppModule)
        .catch(err => console.log(err));
});
