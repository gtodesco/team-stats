/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TeamStatsTestModule } from '../../../test.module';
import { EquipeDetailComponent } from 'app/entities/equipe/equipe-detail.component';
import { Equipe } from 'app/shared/model/equipe.model';

describe('Component Tests', () => {
    describe('Equipe Management Detail Component', () => {
        let comp: EquipeDetailComponent;
        let fixture: ComponentFixture<EquipeDetailComponent>;
        const route = ({ data: of({ equipe: new Equipe(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [TeamStatsTestModule],
                declarations: [EquipeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(EquipeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EquipeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.equipe).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
