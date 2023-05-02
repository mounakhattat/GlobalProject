import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';

import { AppRoutingModule } from './app.routing';
import { NavbarModule } from './shared/navbar/navbar.module';
import { FooterModule } from './shared/footer/footer.module';
import { SidebarModule } from './sidebar/sidebar.module';

import { AppComponent } from './app.component';

import { AdminLayoutComponent } from './layouts/admin-layout/admin-layout.component';
import { CreateReclamationComponent } from './create-reclamation/create-reclamation.component';
import { ListReclamationComponent } from './list-reclamation/list-reclamation.component';
import { UpdateReclamationComponent } from './update-reclamation/update-reclamation.component';
import { CreateEventComponent } from './create-event/create-event.component';
import { ListEventComponent } from './list-event/list-event.component';
import { UpdateEventComponent } from './update-event/update-event.component';
import { Ng2SearchPipe } from 'ng2-search-filter';
import { NgxPaginationModule } from 'ngx-pagination';
import { ChatComponent } from './chat/chat.component';
import { Ng2SearchPipeModule } from 'ng2-search-filter';

@NgModule({
  imports: [
    NgxPaginationModule,
    BrowserAnimationsModule,
    RouterModule,
    HttpClientModule,
    NavbarModule,
    FooterModule,
    SidebarModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    Ng2SearchPipeModule
  ],
  declarations: [
    AppComponent,
    AdminLayoutComponent,
    CreateReclamationComponent,
    ListReclamationComponent,
    UpdateReclamationComponent,
    CreateEventComponent,
    ListEventComponent,
    UpdateEventComponent,
    ChatComponent,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
