import { Routes } from '@angular/router';

import { HomeComponent } from '../../home/home.component';
import { UserComponent } from '../../user/user.component';
import { TablesComponent } from '../../tables/tables.component';
import { TypographyComponent } from '../../typography/typography.component';
import { IconsComponent } from '../../icons/icons.component';
import { MapsComponent } from '../../maps/maps.component';
import { NotificationsComponent } from '../../notifications/notifications.component';
import { UpgradeComponent } from '../../upgrade/upgrade.component';
import { CreateReclamationComponent } from 'app/create-reclamation/create-reclamation.component';
import { ListReclamationComponent } from 'app/list-reclamation/list-reclamation.component';
import { Component } from '@angular/core';
import { UpdateReclamationComponent } from 'app/update-reclamation/update-reclamation.component';
import { CreateEventComponent } from 'app/create-event/create-event.component';
import { ListEventComponent } from 'app/list-event/list-event.component';
import { UpdateEventComponent } from 'app/update-event/update-event.component';
import { ChatComponent } from 'app/chat/chat.component';

export const AdminLayoutRoutes: Routes = [
    { path: 'dashboard',      component: HomeComponent },
    { path: 'user',           component: UserComponent },
    { path: 'create-reclamation',           component: CreateReclamationComponent },
    { path: 'update-reclamation',           component: UpdateReclamationComponent },
    { path: 'list-reclamation',           component: ListReclamationComponent },
    { path: 'create-event',           component: CreateEventComponent },
    { path: 'list-event',           component: ListEventComponent },  
    { path: 'update-event',           component: UpdateEventComponent },
    { path: 'table',          component: TablesComponent },
    { path: 'typography',     component: TypographyComponent },
    { path: 'icons',          component: IconsComponent },
    { path: 'maps',           component: MapsComponent },
    { path: 'notifications',  component: NotificationsComponent },
    { path: 'upgrade',        component: UpgradeComponent },
    { path: 'chat',        component: ChatComponent },
];
