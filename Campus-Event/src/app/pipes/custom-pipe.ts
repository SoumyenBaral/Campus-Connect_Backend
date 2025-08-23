import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'custom'
})
export class CustomPipe implements PipeTransform {

  transform(value: string, ...args: unknown[]): unknown {
    return 'Welcome '+" "+value
    
    ;
  }

}
