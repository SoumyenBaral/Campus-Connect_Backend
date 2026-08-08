import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filterByCategory'
})
export class filterByCategoryPipe implements PipeTransform {

  transform(items: any[], category: string): any[] {
    if (!items || !category) {
      return items;
    }
    // Assumes 'category' is a property on the event object
    return items.filter(item => item.category === category);
  }

}
