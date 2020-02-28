import { inject, bindable, observable } from 'aurelia-framework';

@inject(Element)
export class CustomTable {

	@bindable items;
	@bindable columns;
	@bindable selectionMode = "none";  // "multi", "single"
	@bindable updateSelection;
	@bindable sortingCustomTable;
	@bindable rowClass = "";
	@bindable checkHidden = false;
	@observable selectAll = false;
	@bindable tableContext;
	constructor(element) {
		this.element = element;
	}

	attached() {
		this.checkSelectAll();
	}

	bind(bindingContext, overrideContext) {
		this.parent = bindingContext;
		if (this.tableContext != null){
			this.parent = this.tableContext;
		}
	}

	selectAllChecked() {
		this.items.forEach(i => i.customTableSelected = this.selectAll);
		this.notifySelection();
	}

	checkSelectAll() {
		this.selectAll = this.items.filter(item => { return item.CustomTableSelected; }).length === this.items.length;
	}

	selectRow(item, event, index) {
		if (this.selectionMode === "none" || event.target.tagName === 'A' || event.target.tagName === 'INPUT') {
			return true;
		}
		item.customTableSelected = !item.customTableSelected;
		this.selectCheckboxRow(item, event, index);
	}

	clearSelection(index) {
		this.items.forEach((i, j) => {
			if (j !== index) {
				i.customTableSelected = false;
			}
		});
	}

	selectCheckboxRow(item, event, index) {
		if (this.selectionMode === "single") {
			this.clearSelection(index);
		}
		this.notifySelection();
		this.checkSelectAll();
		return true;
	}

	notifySelection() {
		if (this.updateSelection) {
			if (this.selectionMode === "multi") {
				this.updateSelection.apply(this.parent, [ this.items.filter(i => i.customTableSelected) ]);
			} else if (this.selectionMode === "single") {
				this.updateSelection.apply(this.parent, [ this.items.find(i => i.customTableSelected) ]);
			}
		}
	}

	handleSorting(column) {
		if (!column) {
		  return null;
		}
		if (this.sortingCustomTable) {
			if (column.sortName !== undefined){
				column.sortOption = column.sortOption === 'Asc' ? 'Desc' : 'Asc';
				this.sortingCustomTable.apply(this.parent, [column]);
			}
		}
	}
}
