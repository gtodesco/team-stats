/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { SprintComponentsPage, SprintDeleteDialog, SprintUpdatePage } from './sprint.page-object';

const expect = chai.expect;

describe('Sprint e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let sprintUpdatePage: SprintUpdatePage;
    let sprintComponentsPage: SprintComponentsPage;
    let sprintDeleteDialog: SprintDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Sprints', async () => {
        await navBarPage.goToEntity('sprint');
        sprintComponentsPage = new SprintComponentsPage();
        await browser.wait(ec.visibilityOf(sprintComponentsPage.title), 5000);
        expect(await sprintComponentsPage.getTitle()).to.eq('Sprints');
    });

    it('should load create Sprint page', async () => {
        await sprintComponentsPage.clickOnCreateButton();
        sprintUpdatePage = new SprintUpdatePage();
        expect(await sprintUpdatePage.getPageTitle()).to.eq('Create or edit a Sprint');
        await sprintUpdatePage.cancel();
    });

    it('should create and save Sprints', async () => {
        const nbButtonsBeforeCreate = await sprintComponentsPage.countDeleteButtons();

        await sprintComponentsPage.clickOnCreateButton();
        await promise.all([
            sprintUpdatePage.setNumeroInput('numero'),
            sprintUpdatePage.setPontPlanejadaInput('pontPlanejada'),
            sprintUpdatePage.setPontConcluidasInput('pontConcluidas'),
            sprintUpdatePage.setQtdDiasInput('qtdDias'),
            sprintUpdatePage.equipeSelectLastOption()
        ]);
        expect(await sprintUpdatePage.getNumeroInput()).to.eq('numero');
        expect(await sprintUpdatePage.getPontPlanejadaInput()).to.eq('pontPlanejada');
        expect(await sprintUpdatePage.getPontConcluidasInput()).to.eq('pontConcluidas');
        expect(await sprintUpdatePage.getQtdDiasInput()).to.eq('qtdDias');
        const selectedFinalizada = sprintUpdatePage.getFinalizadaInput();
        if (await selectedFinalizada.isSelected()) {
            await sprintUpdatePage.getFinalizadaInput().click();
            expect(await sprintUpdatePage.getFinalizadaInput().isSelected()).to.be.false;
        } else {
            await sprintUpdatePage.getFinalizadaInput().click();
            expect(await sprintUpdatePage.getFinalizadaInput().isSelected()).to.be.true;
        }
        await sprintUpdatePage.save();
        expect(await sprintUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await sprintComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Sprint', async () => {
        const nbButtonsBeforeDelete = await sprintComponentsPage.countDeleteButtons();
        await sprintComponentsPage.clickOnLastDeleteButton();

        sprintDeleteDialog = new SprintDeleteDialog();
        expect(await sprintDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this Sprint?');
        await sprintDeleteDialog.clickOnConfirmButton();

        expect(await sprintComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
