//读取Excel文件，并将数据更新至数据库

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import com.aheadsoft.mobile.services.DingService;

public class test
{
	
	public static ByteArrayOutputStream createArrayOutputStream(InputStream input)throws Exception{
		ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
		byte[] buffer = new byte[1024];  
		int len;
		while ((len = input.read(buffer)) > -1 ) {  
		   baos.write(buffer, 0, len);  
		}  
	   baos.flush();                
       return baos;
	}
	
	public static String cell_string(HSSFCell cell)
	{
	    if(cell == null)
	    {
	        return "";
	    }
	    
	    DecimalFormat df = new DecimalFormat("#");
        
        switch (cell.getCellType()){
	        case HSSFCell.CELL_TYPE_NUMERIC:{
	        	if(HSSFDateUtil.isCellDateFormatted(cell)){
	                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	                return sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
	            }
	
	            return df.format(cell.getNumericCellValue());
	        }
	        case HSSFCell.CELL_TYPE_STRING:{
	        	String str = cell.getStringCellValue();
	    	    return str != null? str.trim():"";
	        }
	        case HSSFCell.CELL_TYPE_FORMULA:
	            return cell.getCellFormula();
	        case HSSFCell.CELL_TYPE_BLANK:
	            return "";
	        default:
	        {
	        	cell.setCellType(Cell.CELL_TYPE_STRING);
	        	String str = cell.getStringCellValue();
	     	    return str != null? str.trim():"";
	        }
        }
	}
	
	public static String importTps(InputStream excel) throws Exception{
		String result = null;
		//try {
			ByteArrayOutputStream output=createArrayOutputStream(excel);//因为需要用到两次
			HSSFWorkbook wbs = new HSSFWorkbook(new ByteArrayInputStream(output.toByteArray()));
			HSSFSheet sheet1 = wbs.getSheetAt(0);
			HSSFRow row = null;
			
			DingService sv = new DingService();
		
			for (int j = 1; j <=sheet1.getLastRowNum(); j++) {
				row = sheet1.getRow(j);
				if (row == null) {
					continue;
				}
				short cell_index = 0;
				
				String name = cell_string(row.getCell(cell_index++));
				String mobile = cell_string(row.getCell(cell_index++));
				
				//int count = sv.updatePersonMobileByName(name, mobile);
				int count = sv.updatePersonMobileByNameCenter(name, mobile);
				if(count==0 || count>1){
					System.out.println("count:"+count+" name:"+ name +" mobile" + mobile);
				}
			}
			
			sv.commit();
			/*
			List<TzsbPersonXukeVo> itemList = new ArrayList<TzsbPersonXukeVo>();
			TzsbPersonXukeVo item = null;

			for (int j = 1; j <=sheet1.getLastRowNum(); j++) {
				row = sheet1.getRow(j);
				if (row == null) {
					continue;
				}
				int cell_index = 0;
				item = new TzsbPersonXukeVo();
				
				item.setName(StringUtil.cell_string(row.getCell(cell_index++)));
				item.setSfz(StringUtil.cell_string(row.getCell(cell_index++)));
				item.setJob(StringUtil.cell_string(row.getCell(cell_index++)));
				item.setZsbh(StringUtil.cell_string(row.getCell(cell_index++)));
				item.setFzbm(StringUtil.cell_string(row.getCell(cell_index++)));
				item.setXm(StringUtil.cell_string(row.getCell(cell_index++)));
				item.setPzrq(StringUtil.cell_string(row.getCell(cell_index++)));
				item.setYxrq(StringUtil.cell_string(row.getCell(cell_index++)));
				item.setPydw(StringUtil.cell_string(row.getCell(cell_index++)));
				item.setPyxm(StringUtil.cell_string(row.getCell(cell_index++)));
				item.setPyzq(StringUtil.cell_string(row.getCell(cell_index++)));
				item.setDwtelephone(StringUtil.cell_string(row.getCell(cell_index++)));
				item.setDabh(StringUtil.cell_string(row.getCell(cell_index++)));
				item.setFddbr(StringUtil.cell_string(row.getCell(cell_index++)));
				item.setKsjg(StringUtil.cell_string(row.getCell(cell_index++)));
				
				item.setCreateTime(new Date());
				item.setCreateUser(getWebUser().getId());
				
				itemList.add(item);
			}
			
			if("1".equals(scfstps)){
				xk_sv.rmByTableName("t_tzsbpersonxuke");    // 先删除，后保存
			}
			for (TzsbPersonXukeVo tmp : itemList) {
				xk_sv.addData(tmp);
			}
			
			
			xk_sv.commit();
			
		} catch (Exception e) {
			result = "导入失败";
			HibernateUtil.rollbackTransaction();
			log.error(e.getMessage(), e);
		}*/
		
		return result;
	}
	
    public static void main(String args[])
    {
    	
    	/*
    	DingService ds = new DingService();
    	String obj = ds.getDDJsApiTicket();
    	//DD_Ticket dd = ds.getDD_Ticket();
    	System.out.println(obj);*/
    	try {
	    	FileInputStream inputStream = null;
	    	String filePath = "D:\\user.xls";
	    	File file = new File(filePath);
	    	if(!file.exists()){
	    		System.out.println("文件不存在");
	    	}
	    	inputStream = new FileInputStream(file);
	    	String rst = importTps(inputStream);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
   }
